package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.User;

import java.util.List;

public interface FollowService {
    List<User> findFolloweeByFollower(Long followerId);

    List<User> findFollowerByFollowee(Long followeeId);
}
