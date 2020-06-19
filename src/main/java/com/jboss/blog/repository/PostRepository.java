package com.jboss.blog.repository;

import com.jboss.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
      @Query(value = "select * FROM Posts where user_id = ?1", nativeQuery = true)
      List<Post> findPostByUser(Long userId);
      List<Post> findByCategory(Long categoryId);
}

