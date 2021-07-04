package com.syncross.learningspring.domain.user;

import com.syncross.learningspring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    @Column
    private String picture;
    
    @Enumerated(EnumType.STRING) // 기본값은 int
    @Column(nullable = false)
    private Role role;
    
    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name    = name;
        this.email   = email;
        this.picture = picture;
        this.role    = role;
    }
    
    public User updateUser(String name, String picture) {
        this.name    = name;
        this.picture = picture;
        
        return this;
    }
    
    public String getRoleKey(){
        return this.role.getKey();
    }
}