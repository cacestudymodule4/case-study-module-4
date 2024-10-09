package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findByPost(Long postId) {
        return commentRepository.findByPost(postId);
    }
}
