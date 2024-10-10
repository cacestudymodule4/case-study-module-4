package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    Long countByPost(Post post);
}
