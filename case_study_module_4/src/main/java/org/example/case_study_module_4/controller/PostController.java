package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

        @Autowired
        private LikeService likeService;

        @PostMapping("/{postId}/like")
        public ResponseEntity<String> likePost(@PathVariable Long postId, @RequestParam Long userId) {
            likeService.likePost(postId, userId);
            return ResponseEntity.ok("Post liked");
        }

        @GetMapping("/{postId}/likes")
        public ResponseEntity<Long> getLikes(@PathVariable Long postId) {
            Long likeCount = likeService.countLikes(postId);
            return ResponseEntity.ok(likeCount);
        }

}
