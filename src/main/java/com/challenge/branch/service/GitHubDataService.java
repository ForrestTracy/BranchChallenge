package com.challenge.branch.service;

import com.challenge.branch.cache.CacheService;
import com.challenge.branch.client.GitHubClient;
import com.challenge.branch.converter.GitHubRepoResponseToGitHubRepoConverter;
import com.challenge.branch.converter.GitHubUserResponseToGitHubUserConverter;
import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubUserDetails;
import com.challenge.branch.domain.GitHubUserDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GitHubDataService {

    private final CacheService cacheService;
    private final GitHubClient gitHubClient;
    private final GitHubRepoResponseToGitHubRepoConverter gitHubRepoResponseToGitHubRepoConverter;
    private final GitHubUserResponseToGitHubUserConverter gitHubUserResponseToGitHubUserConverter;

    /**
     * This gathers the gitHubUserDetails from GitHub unless cache-only is requested.
     * NOTE - due to GitHub's rate limiting, the option of returning only the cached value was added for testing purposes.
     *
     * @param gitHubUserName Name of the user's data being gathered from GitHub.
     * @param useOnlyCache For testing purposes to return the cached values for the given GitHub user.
     * @return GitHubUserDetails: Complete merged details of the GitHub user account and the user's public repos.
     */
    public GitHubUserDetails getGitHubUserDetails(String gitHubUserName, Boolean useOnlyCache) {
        if (useOnlyCache) {
            return getCachedValues(gitHubUserName);
        }
        return gatherGitHubUserAndRepoData(gitHubUserName);
    }

    private GitHubUserDetails getCachedValues(String gitHubUserName) {
        return cacheService.getGitHubUserDetailsCache(gitHubUserName);
    }

    private GitHubUserDetails gatherGitHubUserAndRepoData(String gitHubUserName) {
        GitHubUserDetailsResponse gitHubUserDetailsResponse = gitHubClient.getGitHubUserDetails(gitHubUserName);
        if (gitHubUserDetailsResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The GitHub username provided was not found.");
        }
        GitHubUserDetails gitHubUserDetails = gitHubUserResponseToGitHubUserConverter.convert(gitHubUserDetailsResponse);
        Set<GitHubRepo> gitHubRepos = gitHubRepoResponseToGitHubRepoConverter.convert(gitHubClient.getGitHubRepos(gitHubUserName));
        gitHubUserDetails.setRepos(gitHubRepos);
        return gitHubUserDetails;
    }

}
