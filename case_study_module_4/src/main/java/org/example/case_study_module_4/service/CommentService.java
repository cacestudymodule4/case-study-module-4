package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;

public interface CommentService {
    Comment createComment(Comment comment);

    Comment gerComment(Long commentId);
}
