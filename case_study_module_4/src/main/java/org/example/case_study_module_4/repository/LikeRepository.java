package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.post.id = :postId")
    List<Like> findByPostId(@Param("postId") Long postId);
}
