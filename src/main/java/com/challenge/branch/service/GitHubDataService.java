package com.challenge.branch.service;

import com.challenge.branch.cache.CacheService;
import com.challenge.branch.client.GitHubClient;
import com.challenge.branch.converter.GitHubRepoResponseToGitHubRepoConverter;
import com.challenge.branch.converter.GitHubUserResponseToGitHubUserConverter;
import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubUserDetails;
import com.challenge.branch.domain.ToDeleteArrayPracticeGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class GitHubDataService {

    private final CacheService cacheService;
    private final GitHubClient gitHubClient;
    private final GitHubRepoResponseToGitHubRepoConverter gitHubRepoResponseToGitHubRepoConverter;
    private final GitHubUserResponseToGitHubUserConverter gitHubUserResponseToGitHubUserConverter;

    // TODO delete this method
    public ToDeleteArrayPracticeGetResponse practiceWebClient() {
        ToDeleteArrayPracticeGetResponse response = gitHubClient.practiceGet();
        return response;
    }
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
        GitHubUserDetails gitHubUserDetails = gitHubUserResponseToGitHubUserConverter.convert(gitHubClient.getGitHubUserDetails(gitHubUserName));
        Set<GitHubRepo> gitHubRepos = gitHubRepoResponseToGitHubRepoConverter.convert(gitHubClient.getGitHubRepos(gitHubUserName));
        gitHubUserDetails.setRepos(gitHubRepos);
        return gitHubUserDetails;
    }
}
