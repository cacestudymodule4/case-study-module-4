package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.LikeService;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/like")
public class LikeRestfulController {
    private final UserService userService;
    private final LikeService likeService;
    private final PostService postService;
    private final NotificationService notificationService;

    public LikeRestfulController(UserService userService,
                                 LikeService likeService, PostService postService, NotificationService notificationService) {
        this.userService = userService;
        this.likeService = likeService;
        this.postService = postService;
        this.notificationService = notificationService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> like(@PathVariable("id") Long postId, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Post post = postService.findPostById(postId);
        Post postLike = likeService.updateLike(postId, user.getId());
        if (postLike!=null) {
            notificationService.sendLikeNotification(user, post);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getLike(@PathVariable("id") Long postId, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        boolean liked = likeService.isLikedByUser(postId, user.getId());
        Long countLike = likeService.countLikes(postId);
        Map<String, Object> response = new HashMap<>();
        response.put("liked", liked);
        response.put("likeCount", countLike);

        return ResponseEntity.ok(response);
    }
}
