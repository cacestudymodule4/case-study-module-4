package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> getLikedByPostId(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    @Override
    public Like getLikedByUserId(Long userId, Long postId) {
        return likeRepository.findByUser(userId, postId);
    }

    @Override
    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public Like addLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public List<User> getUserLikedByPostId(Long postId) {
        return likeRepository.findUserByPostId(postId);
    }
}
