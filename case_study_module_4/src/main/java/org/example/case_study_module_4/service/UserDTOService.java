package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.UserDTO;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.FollowRepository;
import org.example.case_study_module_4.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserDTOService {
    private final PostRepository postRepository;
    private final FriendshipService friendshipService;
    private final FollowRepository postFollowRepository;
    private final MediaService mediaService;

    public UserDTOService(PostRepository postRepository,
                          FriendshipService friendshipService,
                          FollowRepository postFollowRepository,
                          MediaService mediaService) {
        this.postRepository = postRepository;
        this.friendshipService = friendshipService;
        this.postFollowRepository = postFollowRepository;
        this.mediaService = mediaService;
    }

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        Map<Post, String> posts = mediaService.findAll(user);
        int countFollowee = postFollowRepository.findFolloweeByFollower(user.getId()).size();
        int countFollower = postFollowRepository.findFollowerByFollowee(user.getId()).size();
        int countFriends = friendshipService.getFriends(user).size();
        userDTO.setPosts(posts);
        userDTO.setCountFollower(countFollower);
        userDTO.setCountFollowee(countFollowee);
        userDTO.setCountFriends(countFriends);
        return userDTO;
    }
}
