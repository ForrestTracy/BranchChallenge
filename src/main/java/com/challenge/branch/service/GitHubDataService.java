package com.challenge.branch.service;

import com.challenge.branch.cache.CacheService;
import com.challenge.branch.client.GitHubClient;
import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GitHubDataService {

    private final GitHubClient gitHubClient;

    private final CacheService cacheService;
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
        GitHubUserDetails gitHubUserDetails = CONVERTER gitHubClient.getGitHubUserDetails(gitHubUserName);
        Set<GitHubRepo> gitHubRepos = CONVERTER gitHubClient.getGitHubRepos(gitHubUserName);
        gitHubUserDetails.setRepos(gitHubRepos);
        return gitHubUserDetails;
    }
}
