package com.syncross.learningspring.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속되는 엔티티에 매핑 정보가 적용되는 클래스를 지정
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 포함
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate; // createdAt
    
    @LastModifiedDate
    private LocalDateTime modifiedDate; // updatedAy
}