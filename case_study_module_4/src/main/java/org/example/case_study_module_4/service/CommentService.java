package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    List<Comment> findByPost(Long postId);
     Comment addComment(Comment comment, Long postId, Principal principal);

}
