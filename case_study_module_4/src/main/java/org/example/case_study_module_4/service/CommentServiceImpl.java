package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.CommentRepository;
import org.example.case_study_module_4.repository.PostRepository;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findByPost(Long postId) {
        return commentRepository.findByPost(postId);
    }

    @Override
    public Comment addComment(Comment comment, Long postId, Principal principal) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ExpressionException("Post not found"));
        String useEmail = principal.getName();
        User user = userRepository.findByEmail(useEmail);
        comment.setPost(post);
        comment.setUser(user); // Gán user vào comment
        return commentRepository.save(comment);
    }


}
