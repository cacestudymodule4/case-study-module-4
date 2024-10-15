package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
    List<Post> findByUserId(@Param("userId") Long userId);

    List<Post> findPostsByUser(User user);

    @Query("SELECT p FROM Post p WHERE p.deleted = FALSE")
    List<Post> findDeletedPosts();
}
