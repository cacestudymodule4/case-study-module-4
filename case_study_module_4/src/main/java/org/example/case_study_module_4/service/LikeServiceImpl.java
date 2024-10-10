package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.LikeRepository;
import org.example.case_study_module_4.repository.PostRepository;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post updateLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Like existingLike = likeRepository.findByPostAndUser(post, user).orElse(null);
        if (existingLike == null) {
            existingLike = new Like();
            existingLike.setPost(post);
            existingLike.setUser(user);
            existingLike.setLiked(true);
            likeRepository.save(existingLike);

            return post;
        } else {
            existingLike.setLiked(!existingLike.isLiked());
            likeRepository.save(existingLike);
        }

        return null;
    }

    @Override
    public Long countLikes(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        return likeRepository.countLikesByPost(post);
    }

    @Override
    public boolean isLikedByUser(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Like like = likeRepository.findByPostAndUser(post, user).orElse(null);

        return like != null && like.isLiked();
    }
}
