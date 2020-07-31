package com.fivekm_home.charge.domain.USER.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    일반("ROLE_NOMAL", "일반"), // 권한 코드에 항상 ROLE_이 앞에 있어야함.
    등록자("ROLE_REGISTER", "등록자"),
    경비("ROLE_GUARD", "경비"),
    관리자("ROLE_MANAGER", "관리자");

    private final String key;
    private final String title;
}