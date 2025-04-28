package com.took.jpa.social;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements SocialUserInfo {

    private final Map<String, Object> oAuth2UserInfo;

    @Override
    public String getProviderId() {
        return "kakao_" + oAuth2UserInfo.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map)oAuth2UserInfo.get("kakao_account");
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map) oAuth2UserInfo.get("properties");
        return properties.get("nickname").toString();
    }
}
