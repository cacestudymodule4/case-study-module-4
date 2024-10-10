package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Friendship;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface FriendshipService {
    List<User> getFriends(User user);

    String getStatus(User user, User otherUser);

    void deleteFriend(Friendship friendship);

    Friendship getFriendship(User user, User otherUser);

    Friendship createFriendship(User user, User otherUser);

    void updateFriendship(Friendship friendship);
}
