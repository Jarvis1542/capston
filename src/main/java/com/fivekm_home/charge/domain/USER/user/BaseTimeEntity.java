package com.fivekm_home.charge.domain.USER.user;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 이 클래스에 Auditing기능 추가
public abstract class BaseTimeEntity { // 모든 Entity의 상위 클래스가 되어 필드를 자동 관리 역할

    @CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장
    private Date createdDate;

    @LastModifiedDate // 조회된 Entity값이 변경될 때 시간이 자동 저장
    private Date modifiedDate;
}
