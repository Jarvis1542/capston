package com.fivekm_home.charge.config.auth;

import com.fivekm_home.charge.config.auth.dto.OAuthAttributes;
import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.user.Member;
import com.fivekm_home.charge.domain.USER.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        // 현재 로그인 진행 중인 서비스를 구분하는 코드, 네이버 로그인 연동시 구분하기 위해 사용
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest // OAuth2 로그인 진행시 키가 되는 필드값(=Primary key), 구글은 기본적으로 코드 제공(sub), 카톡 네이버 지원X
                .getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes // OAuth2UserService를 통해 가져온 OAtuth2User의 attribute를 담는 클래스
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        System.out.println("registrationId : " + registrationId);
        System.out.println("userNameAttributeName : " + userNameAttributeName);

        Member member = saveOrUpdate(attributes);

        System.out.println(member.getName());
        System.out.println(member.getEmail());

        httpSession.setAttribute("user", new SessionUser(member)); // SessionUser : 세션에 사용자 정보를 저장하는 DTO클래스
                                                                             // 왜 User 클래스를 따로 사용하지 않느냐? 나중에 설명
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) { // (5)
        Member user = memberRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return memberRepository.save(user);
    }
}
