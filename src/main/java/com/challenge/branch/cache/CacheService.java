package com.challenge.branch.cache;

import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubUserDetails;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

@Service
public class CacheService {

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
            .created_at(getOctocatCreatedDate())
            .repos(Set.of(OCTO_REPO_BOYSENBERRY_REPO_1, OCTO_REPO_GIT_CONSORTIUM, OCTO_REPO_HELLO_WORLD))
            .build();
    private final Map<String, GitHubUserDetails> FAKE_CACHE = Map.ofEntries(
            entry("githubuserdetails-octocat", OCTO_GIT_USER_DETAILS)
    );

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    private static Date getOctocatCreatedDate() {
        Date date = null;
        try {
            String dateInString = "2011-01-25 18:44:36";
            date = formatter.parse(dateInString);
        } catch(ParseException e) {
            // do nothing
        }
        return date;
    }

}
