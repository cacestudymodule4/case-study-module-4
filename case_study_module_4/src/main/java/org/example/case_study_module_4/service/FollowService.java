package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Follow;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface FollowService {
    List<User> findFolloweeByFollower(Long followerId);

    List<User> findFollowerByFollowee(Long followeeId);

    void CreateFollower(Follow follow);

    Follow getFollow(User follower, User followee);

    void deleteFollow(Follow follow);
}
