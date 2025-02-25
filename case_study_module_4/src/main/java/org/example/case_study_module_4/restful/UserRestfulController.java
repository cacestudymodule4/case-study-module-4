package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FileStorageService;
import org.example.case_study_module_4.service.FollowService;
import org.example.case_study_module_4.service.FriendshipService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestfulController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileStorageService fileStorageService;
    private final FollowService followService;
    private final FriendshipService friendshipService;

    public UserRestfulController(FollowService followService, FriendshipService friendshipService) {
        this.followService = followService;
        this.friendshipService = friendshipService;
    }

    @PostMapping("/update-avatar")
    public String updateUserAvatar(@RequestParam("profilePicture") MultipartFile profilePicture, Principal principal) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        String avatarUrl = fileStorageService.storeFile(profilePicture);
        userUpdate.setProfilePic(avatarUrl);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }

    @PostMapping("/update-info")
    public String updateUserInfo(Principal principal, @RequestParam("name") String name, @RequestParam("bio") String bio) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        userUpdate.setBio(bio);
        userUpdate.setFullName(name);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }

    @PostMapping("/change-password")
    public String changePassword(Principal principal, @RequestParam("password") String password) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        userUpdate.setPassword(password);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }

    @PostMapping("/list/follower")
    public ResponseEntity<List<User>> listFollowers(@RequestBody User user) {
        List<User> followers = followService.findFollowerByFollowee(user.getId());
        return ResponseEntity.ok(followers);
    }

    @PostMapping("/list/followee")
    public ResponseEntity<List<User>> listFollowee(@RequestBody User user) {
        List<User> followeeList = followService.findFolloweeByFollower(user.getId());
        return ResponseEntity.ok(followeeList);
    }

    @PostMapping("/list/friend")
    public ResponseEntity<List<User>> listFriends(@RequestBody User user) {
        List<User> friendList = friendshipService.getFriends(user);
        return ResponseEntity.ok(friendList);
    }
}
