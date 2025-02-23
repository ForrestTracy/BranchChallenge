package com.challenge.branch.domain;

import lombok.Data;

import java.net.URI;
import java.time.LocalDateTime;

@Data
public class GitHubUserDetailsResponse {

    private URI avatar_url;
    private LocalDateTime created_at;
    private String email;
    private URI html_url;
    private String location;
    private String login;
    private String name;

}