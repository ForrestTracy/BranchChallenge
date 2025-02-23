package com.challenge.branch.service

import com.challenge.branch.cache.CacheService
import com.challenge.branch.client.GitHubClient
import com.challenge.branch.converter.GitHubRepoResponseToGitHubRepoConverter
import com.challenge.branch.converter.GitHubUserResponseToGitHubUserConverter
import com.challenge.branch.domain.GitHubRepo
import com.challenge.branch.domain.GitHubRepoResponse
import com.challenge.branch.domain.GitHubUserDetails
import com.challenge.branch.domain.GitHubUserDetailsResponse
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification


class GitHubDataServiceSpec extends Specification {

    CacheService cacheService = Mock()
    GitHubClient gitHubClient  = Mock()
    GitHubRepoResponseToGitHubRepoConverter gitHubRepoResponseToGitHubRepoConverter  = Mock()
    GitHubUserResponseToGitHubUserConverter gitHubUserResponseToGitHubUserConverter  = Mock()
    def gitHubDataService = new GitHubDataService(
            cacheService, 
            gitHubClient, 
            gitHubRepoResponseToGitHubRepoConverter, 
            gitHubUserResponseToGitHubUserConverter
    )

    def gitHubUserDetailsResponse = new GitHubUserDetailsResponse(
            avatar_url: URI.create("url"),
            created_at: null,
            email: null,
            html_url: URI.create("www.github-url.com"),
            location: "Frisco",
            login: "octocat",
            name: "The Octocat"
    )

    def gitHubRepoResponse = new GitHubRepoResponse(
            name: 'hello-worId',
            html_url: URI.create('https://github.com/octocat/git-consortium')
    )

    def octoConsortium = new GitHubRepo(
            name: 'hello-worId',
            url: URI.create('https://github.com/octocat/git-consortium')
    )
    
    def happyGitHubUserDetails = new GitHubUserDetails(
            user_name: 'octocat',
            display_name: 'The Octocat',
            avatar: URI.create('https://avatars3githubusercontentcom/u/583231?v=4'),
            geo_location: 'San Francisco',
            email: null,
            url: URI.create('https://githubcom/octocat'),
            created_at: null,
            repos: Set.of(octoConsortium)
    )

    void 'get getGitHubUserDetails() happy path'() {
        given:
        def gitHubUserName = 'octocat'

        when:
        def result = gitHubDataService.getGitHubUserDetails(gitHubUserName, false)

        then:
        result == happyGitHubUserDetails
        1 * gitHubClient.getGitHubUserDetails(gitHubUserName) >> gitHubUserDetailsResponse
        1 * gitHubUserResponseToGitHubUserConverter.convert(gitHubUserDetailsResponse) >> happyGitHubUserDetails
        1 * gitHubClient.getGitHubRepos(gitHubUserName) >> [gitHubRepoResponse]
        1 * gitHubRepoResponseToGitHubRepoConverter.convert([gitHubRepoResponse]) >> [octoConsortium]
        0 * _
    }

    void '404 properly raised when gitHubClient.getGitHubUserDetails() returns nothing'() {
        given:
        def gitHubUserName = 'not-octocat'

        when:
        def result = gitHubDataService.getGitHubUserDetails(gitHubUserName, false)

        then:
        1 * gitHubClient.getGitHubUserDetails(gitHubUserName) >> null
        thrown(ResponseStatusException)
        0 * _
    }

    void 'gatherGitHubUserAndRepoData() Properly handles when user no repos'() {
        given:
        def gitHubUserName = 'octocat'

        when:
        def result = gitHubDataService.getGitHubUserDetails(gitHubUserName, false)

        then:
        result == happyGitHubUserDetails
        1 * gitHubClient.getGitHubUserDetails(gitHubUserName) >> gitHubUserDetailsResponse
        1 * gitHubUserResponseToGitHubUserConverter.convert(gitHubUserDetailsResponse) >> happyGitHubUserDetails
        1 * gitHubClient.getGitHubRepos(gitHubUserName) >> []
        1 * gitHubRepoResponseToGitHubRepoConverter.convert([]) >> []
        happyGitHubUserDetails.repos.isEmpty()
        0 * _
    }

    /*
        TODO
            - More testing needed around error states return from WebClient
     */

}
