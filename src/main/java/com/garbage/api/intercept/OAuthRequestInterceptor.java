package com.garbage.api.intercept;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthRequestInterceptor /*implements RequestInterceptor*/ {
/*
    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer "
                + oAuth2AuthorizedClientManager
                .authorize(OAuth2AuthorizeRequest
                        .withClientRegistrationId("internal-client")
                        .principal("internal")
                        .build())
                .getAccessToken().getTokenValue());
    }*/
}
