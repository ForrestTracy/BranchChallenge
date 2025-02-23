package com.challenge.branch.cache;

import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubUserDetails;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

@Service
public class CacheService {

    /**
     * This is not a real cache, but was implemented purely for testing purposes given GitHub's rate limiting restrictions.
     *
     * @param gitHubUserName Name of the user's data being gathered from the Fake Cache
     * @return "cached" value of the GitHubUserDetails. Returns null if not present.
     */
    public GitHubUserDetails getGitHubUserDetailsCache(String gitHubUserName) {
        return FAKE_CACHE.get(getGitHubUserDetailsCacheKey(gitHubUserName));
    }

    private String getGitHubUserDetailsCacheKey(String gitHubUserName) {
        return String.format("githubuserdetails-%s", gitHubUserName);
    }

    private final GitHubRepo OCTO_REPO_BOYSENBERRY_REPO_1 = GitHubRepo.builder()
            .name("boysenberry-repo-1")
            .url(URI.create("https://github.com/octocat/boysenberry-repo-1"))
            .build();

    private final GitHubRepo OCTO_REPO_GIT_CONSORTIUM = GitHubRepo.builder()
            .name("hello-worId")
            .url(URI.create("https://github.com/octocat/git-consortium"))
            .build();

    private final GitHubRepo OCTO_REPO_HELLO_WORLD = GitHubRepo.builder()
            .name("git-consortium")
            .url(URI.create("https://github.com/octocat/hello-worId"))
            .build();
    private final GitHubUserDetails OCTO_GIT_USER_DETAILS = GitHubUserDetails.builder()
            .user_name("octocat")
            .display_name("The Octocat")
            .avatar(URI.create("https://avatars3.githubusercontent.com/u/583231?v=4"))
            .geo_location("San Francisco")
            .email(null)
            .url(URI.create("https://github.com/octocat"))
            .created_at(null) //getOctocatCreatedDate())
            .repos(Set.of(OCTO_REPO_BOYSENBERRY_REPO_1, OCTO_REPO_GIT_CONSORTIUM, OCTO_REPO_HELLO_WORLD))
            .build();
    private final Map<String, GitHubUserDetails> FAKE_CACHE = Map.ofEntries(
            entry("githubuserdetails-octocat", OCTO_GIT_USER_DETAILS)
    );

}
