package com.challenge.branch.controller;

import com.challenge.branch.domain.GitHubUserDetails;
import com.challenge.branch.domain.ToDeleteArrayPracticeGetResponse;
import com.challenge.branch.service.GitHubDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/github-data")
@AllArgsConstructor
public class GitHubDataController {

    private final GitHubDataService gitHubDataService;

    @GetMapping("/{gitHubUserName}")
    public GitHubUserDetails gitHubUserDetails(@PathVariable String gitHubUserName, @RequestParam("useOnlyCache") Boolean useOnlyCache) {
        return gitHubDataService.getGitHubUserDetails(gitHubUserName, useOnlyCache);
    }

    @GetMapping("/practice")
    public ToDeleteArrayPracticeGetResponse practiceWebClient() {
        return gitHubDataService.practiceWebClient();
    }

}
