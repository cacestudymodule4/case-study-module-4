package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    List<Like> findByPostId(@Param("postId") Long postId);

    @Query("SELECT l FROM Like l WHERE l.post.id = :postId AND l.user.id = :userId")
    Like findByUser(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("SELECT l.user FROM Like l WHERE l.post.id = :postId")
    List<User> findUserByPostId(@Param("postId") Long postId);
}
