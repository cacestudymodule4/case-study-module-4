package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;
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
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment gerComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
