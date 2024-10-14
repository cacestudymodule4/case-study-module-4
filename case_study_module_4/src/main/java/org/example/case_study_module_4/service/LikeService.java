package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface LikeService {
    List<Like> getLikedByPostId(Long postId);

    Like getLikedByUserId(Long userId, Long postId);

    void deleteLike(Like like);

    Like addLike(Like like);

    List<User> getUserLikedByPostId(Long postId);
}
