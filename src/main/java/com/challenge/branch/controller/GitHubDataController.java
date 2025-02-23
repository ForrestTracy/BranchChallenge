package com.challenge.branch.controller;

import com.challenge.branch.domain.GitHubUserDetails;
import com.challenge.branch.service.GitHubDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="/github-data")
@AllArgsConstructor
public class GitHubDataController {

    private final GitHubDataService gitHubDataService;

    /**
     * Request the user details and associated repos from GitHub
     *
     * @param gitHubUserName Name of the user's data being gathered from GitHub.
     * @param useOnlyCache For testing purposes to return the cached values for the given GitHub user. Defaults to false.
     * @return GitHubUserDetails: Complete merged details of the GitHub user account and the user's public repos.
     */
    @GetMapping("/{gitHubUserName}")
    public GitHubUserDetails gitHubUserDetails(
            @PathVariable String gitHubUserName,
            @RequestParam(value = "useOnlyCache", required = false, defaultValue = "false") Boolean useOnlyCache) {
        if (gitHubUserName == null || gitHubUserName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a gitHub user name to reference.");
        }
        return gitHubDataService.getGitHubUserDetails(gitHubUserName, useOnlyCache);
    }

}
