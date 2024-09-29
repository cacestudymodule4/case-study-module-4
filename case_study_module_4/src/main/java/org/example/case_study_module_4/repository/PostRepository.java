package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
