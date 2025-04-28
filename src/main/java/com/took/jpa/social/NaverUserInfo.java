package com.took.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class NaverUserInfo implements SocialUserInfo{
    private final Map<String, Object> oAuth2UserInfo;

    @Override
    public String getProviderId() {
        Map<String, Object> response = (Map) oAuth2UserInfo.get("response");
        return "naver_" + response.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        Map<String, Object> NaverAccount = (Map)oAuth2UserInfo.get("response");
        return NaverAccount.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> NaverAccount = (Map) oAuth2UserInfo.get("response");
        return NaverAccount.get("name").toString();
    }
}
