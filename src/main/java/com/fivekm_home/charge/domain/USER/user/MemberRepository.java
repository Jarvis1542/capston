package com.fivekm_home.charge.domain.USER.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email); // 이미 가입한 회원인지 처음인지 확인
}
