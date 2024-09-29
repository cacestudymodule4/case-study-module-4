package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
