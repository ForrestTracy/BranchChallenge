package com.challenge.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GitHubUserDetails {

    private URI avatar;
    private LocalDateTime created_at;
    private String display_name;
    private String email;
    private String geo_location;
    private Set<GitHubRepo> repos;
    private URI url;
    private String user_name;

}
