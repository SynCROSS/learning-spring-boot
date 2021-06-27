package com.syncross.learningspring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity 타입, PK 타입>
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT P FROM Posts P ORDER BY P.id DESC")
    List<Posts> findAllPostsOrderByDesc();
}