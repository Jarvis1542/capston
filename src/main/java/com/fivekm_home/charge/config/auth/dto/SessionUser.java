package com.fivekm_home.charge.config.auth.dto;

import com.fivekm_home.charge.domain.USER.Kakao;
import com.fivekm_home.charge.domain.USER.KakaoJoin;
import com.fivekm_home.charge.domain.USER.KakaoLogin;
import com.fivekm_home.charge.domain.USER.Login;
import com.fivekm_home.charge.domain.USER.user.Member;
import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 이 클래스는 인증된 사용자 정보만 필요
    private String email;
    private String name;
    private String picture;
    private Role role;

    // 소셜 로그인
    public SessionUser(Member member){
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
    }

    // 회원가입 로그인
    public SessionUser(Login login){
        this.name = login.getName();
        this.email = login.getEmail();
        this.role = login.getRole();
    }

    // 카카오 로그인
    public SessionUser(KakaoLogin kakao){
        this.name = kakao.getName();
        this.email = kakao.getEmail();
        this.picture = kakao.getPicture();
        this.role = kakao.getRole();
    }

    // 카카오 로그인
    public SessionUser(Kakao kakao){
        this.name = kakao.getName();
        this.email = kakao.getEmail();
        this.picture = kakao.getPicture();
        this.role = kakao.getRole();
    }

    // 카카오 회원가입 후 로그인
    public SessionUser(KakaoJoin kakaoJoin){
        this.name = kakaoJoin.getName();
        this.email = kakaoJoin.getEmail();
        this.picture = kakaoJoin.getPicture();
        this.role = kakaoJoin.getRole();
    }
}
