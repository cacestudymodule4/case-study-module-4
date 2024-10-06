package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Media;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
    @Query("SELECT m FROM Media m WHERE m.post.id = :postId")
    List<Media> findByPostId(@Param("postId") Long postId);
}
