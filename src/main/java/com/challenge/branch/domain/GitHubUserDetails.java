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

    private String user_name;
    private String display_name;
    private String avatar;
    private String geo_location;
    private String email;
    private URI url;
    private Date created_at;
    private Set<GitHubRepo> repos;

}
