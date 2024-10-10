package org.example.case_study_module_4.service;

public interface LikeService {
    void updateLike(Long postId, Long userId);

    Long countLikes(Long postId);
}
