package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.LikeService;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/like")
public class LikeRestfulController {
    private final UserService userService;
    private final LikeService likeService;
    private final PostService postService;
    private final NotificationService notificationService;

    public LikeRestfulController(UserService userService,
                                 LikeService likeService,
                                 PostService postService,
                                 NotificationService notificationService) {
        this.userService = userService;
        this.likeService = likeService;
        this.postService = postService;
        this.notificationService = notificationService;
    }

    @PostMapping("/likePost")
    public ResponseEntity<Like> like(@RequestBody Post post, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Like like = likeService.getLikedByUserId(user.getId(), post.getId());
        if (like == null) {
            like = new Like();
            like.setUser(user);
            like.setPost(post);
            post = postService.findPostById(post.getId());
            if (!Objects.equals(user.getId(), post.getUser().getId())) {
                notificationService.sendLikeNotification(user, post);
            }
            return ResponseEntity.ok(likeService.addLike(like));
        } else {
            likeService.deleteLike(like);
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/list")
    public ResponseEntity<List<Like>> list(@RequestBody Post post) {
        return ResponseEntity.ok(likeService.getLikedByPostId(post.getId()));
    }
}
