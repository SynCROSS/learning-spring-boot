package com.syncross.learningspring.domain.posts;

import com.syncross.learningspring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Getter // 클래스 내 모든 필드 의 get 메소드 생성
@NoArgsConstructor //  기본 생성자 자동 추가
@Entity // 테이블
public class Posts extends BaseTimeEntity {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;
    
    // @Column은 원래 없어도 되지만 조건을 넣기 위해 사용함.
    @Column(length = 500, nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    
    private String author;
    
    @Builder // 생성자의 문제점 해결. 빌더 패턴 클래스 생성. 생성자 상단에 선언서 생성자에 포함된 필드만 빌드에 포함
    public Posts(String title, String content, String author) {
        this.title   = title;
        this.content = content;
        this.author  = author;
    }
    
    public void updatePost(String title, String content) {
        this.title   = title;
        this.content = content;
    }
}