package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Follow;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FollowService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/follow")
public class FollowRestfulController {
    private final FollowService followService;
    private final UserService userService;

    public FollowRestfulController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    @PostMapping("/status")
    public ResponseEntity<Boolean> status(@RequestBody User otherUser, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Follow follow = followService.getFollow(user, otherUser);
        if (follow == null) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody User otherUser, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Follow follow = followService.getFollow(user, otherUser);
        followService.deleteFollow(follow);
        return ResponseEntity.ok(false);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody User otherUser, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Follow follow = new Follow();
        follow.setFollower(user);
        follow.setFollowee(otherUser);
        followService.CreateFollower(follow);
        return ResponseEntity.ok(true);
    }
}
