package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.LikeService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/like")
public class LikeRestfulController {
    private final UserService userService;
    private final LikeService likeService;

    public LikeRestfulController(UserService userService,
                                 LikeService likeService) {
        this.userService = userService;
        this.likeService = likeService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> like(@PathVariable("id") Long postId, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        likeService.updateLike(postId, user.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getLike(@PathVariable("id") Long postId, Principal principal) {
        Long countLike = likeService.countLikes(postId);
        return ResponseEntity.ok(countLike);
    }
}
