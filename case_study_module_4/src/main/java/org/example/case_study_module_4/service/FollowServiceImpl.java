package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    public List<User> findFolloweeByFollower(Long followerId) {
        return followRepository.findFolloweeByFollower(followerId);
    }
}
