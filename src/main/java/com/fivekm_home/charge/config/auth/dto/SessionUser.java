package com.fivekm_home.charge.config.auth.dto;

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

    public SessionUser(Member member){    // 소셜 로그인
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
    }

    public SessionUser(Login login){  // 회원가입 로그인
        this.name = login.getName();
        this.email = login.getEmail();
        this.role = login.getRole();
    }
}
