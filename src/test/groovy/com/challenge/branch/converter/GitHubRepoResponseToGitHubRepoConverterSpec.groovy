package com.challenge.branch.converter

import com.challenge.branch.domain.GitHubRepoResponse
import spock.lang.Specification

class GitHubRepoResponseToGitHubRepoConverterSpec extends Specification {

    def converter = new GitHubRepoResponseToGitHubRepoConverter()

    def gitHubRepoResponse = new GitHubRepoResponse(
            html_url: URI.create('https://github.com/octocat/boysenberry-repo-1'),
            name: 'boysenberry-repo-1'
    )

    def NULLS_gitHubRepoResponse = new GitHubRepoResponse(
            html_url: null,
            name: null
    )

    GitHubRepoResponse[] responseArray = [gitHubRepoResponse]
    GitHubRepoResponse[] NULL_ResponseArray = [NULLS_gitHubRepoResponse]

    void 'convert() happy path'() {
        when:
        def result = converter.convert(responseArray)

        then:
        result[0].name == gitHubRepoResponse.name
        result[0].url == gitHubRepoResponse.html_url
        0 * _
    }

    void 'convert() properly handles null input fields'() {
        when:
        def result = converter.convert(NULL_ResponseArray)

        then:
        result[0].name == null
        result[0].url == null
        0 * _
    }

    void 'convert() properly handles null method param input'() {
        when:
        def result = converter.convert(null)

        then:
        result.isEmpty()
        0 * _
    }
}
