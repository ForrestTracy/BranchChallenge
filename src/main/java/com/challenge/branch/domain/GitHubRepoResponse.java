package com.challenge.branch.domain;

import lombok.Data;

import java.net.URI;

@Data
public class GitHubReposResponse {

    private URI html_url;
    private String name;

}
