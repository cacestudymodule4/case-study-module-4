package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Follow;
import org.example.case_study_module_4.model.Friendship;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FollowService;
import org.example.case_study_module_4.service.FriendshipService;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/friend")
public class FriendshipRestfulController {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final FollowService followService;
    private final NotificationService notificationService;

    public FriendshipRestfulController(UserService userService,
                                       FriendshipService friendshipService,
                                       FollowService followService, NotificationService notificationService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.followService = followService;
        this.notificationService = notificationService;
    }

    @PostMapping("/status")
    public ResponseEntity<?> status(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.getFriendship(user, otherUser);
        if (friendship == null) {
            return ResponseEntity.ok("notFriend");
        }
        return ResponseEntity.ok(friendship);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.getFriendship(user, otherUser);
        friendshipService.deleteFriend(friendship);
        Follow follow = followService.getFollow(user, otherUser);
        if (follow != null) {
            followService.deleteFollow(follow);
        }
        return ResponseEntity.ok("notFriend");
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.createFriendship(user, otherUser);
        notificationService.sendFriendNotification(user, otherUser);
        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowee(otherUser);
        followService.CreateFollower(follow);
        return ResponseEntity.ok(friendship.getStatus());
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.getFriendship(user, otherUser);
        friendshipService.deleteFriend(friendship);
        return ResponseEntity.ok("notFriend");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> accept(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.getFriendship(user, otherUser);
        friendship.setStatus("accepted");
        friendshipService.updateFriendship(friendship);
        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowee(otherUser);
        followService.CreateFollower(follow);
        return ResponseEntity.ok(friendship.getStatus());
    }

    @PostMapping("/reject")
    public ResponseEntity<String> reject(@RequestBody User otherUser, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Friendship friendship = friendshipService.getFriendship(user, otherUser);
        friendshipService.deleteFriend(friendship);
        return ResponseEntity.ok("notFriend");
    }
}
