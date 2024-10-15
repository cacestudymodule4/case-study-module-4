package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostRestfulController {
    private final PostService postService;
    private final UserService userService;

    public PostRestfulController(PostService postService,
                                 UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<Post, List<Media>>> createPost(
            @RequestParam("content") String content,
            @RequestParam("media") MultipartFile[] media,
            Principal principal) {
        Post post = new Post();
        post.setContent(content);
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        post.setUser(user);
        return ResponseEntity.ok(postService.createPost(post, media));
    }

    @PostMapping("/edit")
    public ResponseEntity<Post> editPost(@RequestBody Post post) {
        Post postOld = postService.findPostById(post.getId());
        postOld.setContent(post.getContent());
        return ResponseEntity.ok(postService.updatePost(postOld));
    }
}
