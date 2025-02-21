package com.challenge.branch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.URI;

@Data
@Builder
@AllArgsConstructor
public class GitHubRepo {

    private String name;
    private URI url;

}
