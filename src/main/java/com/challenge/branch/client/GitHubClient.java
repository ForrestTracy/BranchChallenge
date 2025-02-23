package com.challenge.branch.client;

import com.challenge.branch.domain.GitHubRepoResponse;
import com.challenge.branch.domain.GitHubUserDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;

@Service
public class GitHubClient {

    @Value("${gitHubBaseUrl}")
    private String baseUrl;

    WebClient webClient = WebClient.create();

    /**
     * Call the GitHub API and gather the user details if present.
     *
     * @param gitHubUserName Name of the user whose GitHub profile data is being gathered.
     * @return GitHubUserDetailsResponse: Response from the GitHub user's profile. Maps only the fields needed from the GitHub response.
     */
    public GitHubUserDetailsResponse getGitHubUserDetails(String gitHubUserName) {
        return webClient.get()
                .uri(getGitHubUserUrl(gitHubUserName))
                .retrieve()
                .bodyToMono(GitHubUserDetailsResponse.class)
                .block();
    }

    /**
     * Call the GitHub API and gather the user's associated repositories' details
     *
     * @param gitHubUserName Name of the user whose repositories' data is being gathered. Maps only the fields needed from the GitHub response.
     * @return GitHubRepoResponse[]: Array of GitHubRepoResponses
     */
    public GitHubRepoResponse[] getGitHubRepos(String gitHubUserName) {
        return webClient.get()
                .uri(getGitHubReposUrl(gitHubUserName))
                .retrieve()
                .bodyToMono(GitHubRepoResponse[].class)
                .block();
    }

    private String getGitHubUserUrl(String gitHubUserName) {
        String gitHubUserUrl = null;
        try {
            gitHubUserUrl = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .path("/users")
                    .path("/" + gitHubUserName)
                    .build()
                    .toUri()
                    .toURL()
                    .toString();
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        return gitHubUserUrl;
    }

    private String getGitHubReposUrl(String gitHubUserName) {
        String gitHubReposUrl = null;
        try {
            gitHubReposUrl = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .path("/users")
                    .path("/" + gitHubUserName)
                    .path("/repos")
                    .build()
                    .toUri()
                    .toURL()
                    .toString();
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        }
        return gitHubReposUrl;
    }

}
