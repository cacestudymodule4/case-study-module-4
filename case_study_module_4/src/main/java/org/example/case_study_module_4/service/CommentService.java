package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);

    List<Comment> findAll();

    void deleteComment(Long id);
}
