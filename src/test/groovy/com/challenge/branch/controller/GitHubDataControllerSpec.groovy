package com.challenge.branch.controller

import com.challenge.branch.domain.GitHubUserDetails
import com.challenge.branch.service.GitHubDataService
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

class GitHubDataControllerSpec extends Specification {

    GitHubDataService gitHubDataService = Mock()
    def gitHubDataController = new GitHubDataController(gitHubDataService)

    void 'get gitHubUserDetails() happy path'() {
        given:
        def gitHubUserName = "test-name"
        def gitHubUserDetails = GitHubUserDetails.builder().build()

        when:
        def result = gitHubDataController.gitHubUserDetails(gitHubUserName, false)

        then:
        result == gitHubUserDetails
        1 * gitHubDataService.getGitHubUserDetails(gitHubUserName, false) >> gitHubUserDetails
        0 * _
    }

    void 'get gitHubUserDetails() with null user'() {
        given:
        def gitHubUserName = null
        def gitHubUserDetails = GitHubUserDetails.builder().build()

        when:
        gitHubDataController.gitHubUserDetails(gitHubUserName, false)

        then:
        thrown(ResponseStatusException)
        0 * _
    }

    void 'get gitHubUserDetails() with empty String user'() {
        given:
        def gitHubUserName = "  "
        def gitHubUserDetails = GitHubUserDetails.builder().build()

        when:
        gitHubDataController.gitHubUserDetails(gitHubUserName, false)

        then:
        thrown(ResponseStatusException)
        0 * _
    }

}
