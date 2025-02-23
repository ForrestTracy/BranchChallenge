package com.challenge.branch.converter;

import com.challenge.branch.domain.GitHubUserDetails;
import com.challenge.branch.domain.GitHubUserDetailsResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class GitHubUserResponseToGitHubUserConverter implements Converter<GitHubUserDetailsResponse, GitHubUserDetails> {

    /**
     * Convert the response from the GitHub user profile data gathered to the needed GitHubUserDetails Object.
     *
     * @param gitHubUserDetailsResponse the source object to convert gathered from GitHub
     * @return GitHubUserDetails: The data formatted as needed to provide to the calling agent.
     */
    @Override
    public GitHubUserDetails convert(GitHubUserDetailsResponse gitHubUserDetailsResponse) {
        return GitHubUserDetails.builder()
                .avatar(gitHubUserDetailsResponse.getAvatar_url())
                .created_at(gitHubUserDetailsResponse.getCreated_at())
                .display_name(gitHubUserDetailsResponse.getName())
                .email(gitHubUserDetailsResponse.getEmail())
                .geo_location(gitHubUserDetailsResponse.getLocation())
                .url(gitHubUserDetailsResponse.getHtml_url())
                .user_name(gitHubUserDetailsResponse.getLogin())
                .build();
    }

}
