package com.challenge.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class GitHubUserDetails {

    private URI avatar;
    private Date created_at;
    private String display_name;
    private String email;
    private String geo_location;
    private Set<GitHubRepo> repos;
    private URI url;
    private String user_name;

}
