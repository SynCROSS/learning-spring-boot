package com.syncross.learningspring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity 타입, PK 타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {}