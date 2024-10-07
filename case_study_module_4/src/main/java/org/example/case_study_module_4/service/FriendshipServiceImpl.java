package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.FriendshipRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<User> getFriends(User user) {
        List<User> friends = new ArrayList<>();
        String status = "accepted";
        friends.addAll(friendshipRepository.findFriendsByUserId(user.getId(), status));
        friends.addAll(friendshipRepository.findFriendsByFriendId(user.getId(), status));
        return friends;
    }
}
