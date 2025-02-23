package com.challenge.branch.converter;

import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubReposArrayResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GitHubRepoResponseToGitHubRepoConverter implements Converter<GitHubReposArrayResponse, Set<GitHubRepo>> {

    @Override
    public Set<GitHubRepo> convert(GitHubReposArrayResponse gitHubReposArrayResponse) {

        return Arrays.stream(gitHubReposArrayResponse.getRepos())
                .map( repoResponse -> GitHubRepo.builder()
                        .name(repoResponse.getName())
                        .url(repoResponse.getHtml_url())
                        .build())
                        .collect(Collectors.toSet());
    }

}
