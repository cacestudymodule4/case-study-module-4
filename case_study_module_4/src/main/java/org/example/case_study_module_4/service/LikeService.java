package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Post;

public interface LikeService {
    Post updateLike(Long postId, Long userId);

    Long countLikes(Long postId);

    boolean isLikedByUser(Long postId, Long userId);
}
