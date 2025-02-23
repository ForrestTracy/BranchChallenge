package com.challenge.branch.converter;

import com.challenge.branch.domain.GitHubRepo;
import com.challenge.branch.domain.GitHubRepoResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GitHubRepoResponseToGitHubRepoConverter implements Converter<GitHubRepoResponse[], Set<GitHubRepo>> {

    /**
     * Convert the response from the GitHub repo to the GitHubRepo Object format.
     *
     * @param gitHubRepoResponse the source object to convert gathered from GitHub.
     * @return Set<GitHubRepo>: The data formatted as needed to provide to the calling agent.
     */
    @Override
    public Set<GitHubRepo> convert(GitHubRepoResponse[] gitHubRepoResponse) {

        if (gitHubRepoResponse == null || gitHubRepoResponse.length < 1) {
            return Set.of();
        }

        return Arrays.stream(gitHubRepoResponse).map(repoResponse ->
                GitHubRepo.builder()
                    .name(repoResponse.getName())
                    .url(repoResponse.getHtml_url())
                    .build())
                .collect(Collectors.toSet());
    }

}
