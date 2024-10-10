package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    List<Like> findByPostId(@Param("postId") Long postId);

    Optional<Like> findByPostAndUser(Post post, User user);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post = :post AND l.isLiked = true")
    Long countLikesByPost(Post post);
}
