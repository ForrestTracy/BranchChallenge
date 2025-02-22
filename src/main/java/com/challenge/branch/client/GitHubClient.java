package com.challenge.branch.client;

import com.challenge.branch.domain.GitHubReposArrayResponse;
import com.challenge.branch.domain.GitHubUserDetailsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;

public class GitHubClient {

    @Value("${gitHubBaseUrl}")
    private String baseUrl;

    WebClient webClient = WebClient.create();

    public GitHubUserDetailsResponse getGitHubUserDetails(String gitHubUserName) {
        return webClient.get()
                .uri(getGitHubUserUrl(gitHubUserName))
                .retrieve()
                .bodyToMono(GitHubUserDetailsResponse.class)
                .block();
    }

    public GitHubReposArrayResponse getGitHubRepos(String gitHubUserName) {
        return webClient.get()
                .uri(getGitHubReposUrl(gitHubUserName))
//                .body(Mono.just(employee), Employee.class) // todo clean up if needed
                .retrieve()
                .bodyToMono(GitHubReposArrayResponse.class)
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
        System.out.println("Url should be -> https://api.github.com/users/octocat");  // todo delete log
        System.out.println("Url is -> " + gitHubUserUrl);  // todo delete log
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
        System.out.println("Url should be -> https://api.github.com/users/octocat/repos");  // todo delete log
        System.out.println("Url is -> " + gitHubReposUrl);  // todo delete log
        return gitHubReposUrl;
    }

}
