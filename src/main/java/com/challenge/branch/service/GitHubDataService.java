package com.challenge.branch.service;

import com.challenge.branch.cache.CacheService;
import com.challenge.branch.domain.GitHubUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubDataService {

    private final CacheService cacheService;
    public GitHubUserDetails getGitHubUserDetails(String gitHubUserName, Boolean useOnlyCache) {
        return useOnlyCache ? getCachedValues(gitHubUserName) : null; // todo client call
    }

    private GitHubUserDetails getCachedValues(String gitHubUserName) {
        return cacheService.getGitHubUserDetailsCache(gitHubUserName);
    }

}
