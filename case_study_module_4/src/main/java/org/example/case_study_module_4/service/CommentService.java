package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);

    Comment gerComment(Long commentId);

    void deleteComment(Long commentId);

    List<Comment> findAll();
}
