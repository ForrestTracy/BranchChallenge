package com.challenge.branch.converter

import com.challenge.branch.domain.GitHubUserDetailsResponse
import spock.lang.Specification

class GitHubUserResponseToGitHubUserConverterSpec extends Specification {

    GitHubUserResponseToGitHubUserConverter converter = new GitHubUserResponseToGitHubUserConverter()

    def gitHubUserDetailsResponse = new GitHubUserDetailsResponse(
            avatar_url: URI.create('https://avatars.githubusercontent.com/u/583231?v=4'),
            created_at: null ,
            email: null,
            html_url: URI.create('https://github.com/octocat'),
            location: 'San Francisco',
            login: 'octocat',
            name: 'The Octocat'
    )

    def NULLS_GitHubUserDetailsResponse = new GitHubUserDetailsResponse(
            avatar_url: null,
            created_at: null ,
            email: null,
            html_url: null,
            location: null,
            login: null,
            name: null
    )

    void 'convert() happy path'() {
        when:
        def result = converter.convert(gitHubUserDetailsResponse)

        then:
        result.user_name == gitHubUserDetailsResponse.login
        result.display_name == gitHubUserDetailsResponse.name
        result.avatar == gitHubUserDetailsResponse.avatar_url
        result.geo_location == gitHubUserDetailsResponse.location
        result.email == gitHubUserDetailsResponse.email
        result.url == gitHubUserDetailsResponse.html_url
        result.created_at == gitHubUserDetailsResponse.created_at
        result.repos == null
        0 * _
    }

    void 'convert() properly handles null input fields'() {
        when:
        def result = converter.convert(NULLS_GitHubUserDetailsResponse)

        then:
        result.user_name == null
        result.display_name == null
        result.avatar == null
        result.geo_location == null
        result.email == null
        result.url == null
        result.created_at == null
        result.repos == null
        0 * _
    }

}
