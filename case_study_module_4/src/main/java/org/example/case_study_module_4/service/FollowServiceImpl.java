package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Follow;
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

    @Override
    public List<User> findFollowerByFollowee(Long followeeId) {
        return followRepository.findFollowerByFollowee(followeeId);
    }

    @Override
    public void CreateFollower(Follow follow) {
        Follow f = getFollow(follow.getFollower(), follow.getFollowee());
        if (f == null) {
            followRepository.save(follow);
        }
    }

    @Override
    public Follow getFollow(User follower, User followee) {
        return followRepository.findByFollowerAndFollowee(follower.getId(), followee.getId());
    }

    @Override
    public void deleteFollow(Follow follow) {
        followRepository.delete(follow);
    }
}
