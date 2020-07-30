package com.fivekm_home.charge.domain.USER.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq", initialValue = 1, allocationSize = 1)
@Entity
public class Member extends BaseTimeEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
//    @Column(columnDefinition = "number")
//    private Long memSeq;

    @Id
    @Column(columnDefinition = "varchar2(100)")
    private String email;

    @Column(columnDefinition = "varchar2(100)")
    private String password;

    @Column(columnDefinition = "varchar2(30)")
    private String name;

    @Column(columnDefinition = "varchar2(20)")
    private String phone;

    @Column(columnDefinition = "varchar2(1000)")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar2(10)")
    private Role role;

    @Builder
    public Member(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}