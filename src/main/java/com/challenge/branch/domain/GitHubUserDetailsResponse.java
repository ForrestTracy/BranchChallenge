package com.challenge.branch.domain;

import java.util.Date;

public class GitHubUserDetailsResponse {

    private String login; // maps to GitHubUserDetails.user_name
    private String name; // maps to GitHubUserDetails.display_name
    private String avatar_url; // maps to GitHubUserDetails.avatar
    private String location; // maps to GitHubUserDetails.geo_location
    private String email; // maps to GitHubUserDetails.email
    private String html_url; // maps to GitHubUserDetails.url
    private Date created_at; // maps to GitHubUserDetails.created_at

}