package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.DTO.CommentRequest;
import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.CommentService;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/comment")
public class CommentRestfulController {
    private final CommentService commentService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final PostService postService;

    public CommentRestfulController(CommentService commentService,
                                    UserService userService, NotificationService notificationService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.notificationService = notificationService;
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        Post post = postService.findPostById(commentRequest.getPost().getId());
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setPost(commentRequest.getPost());
        comment = commentService.createComment(comment);
        notificationService.sendCommentNotification(user, post);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/edit")
    public ResponseEntity<Comment> editComment(@RequestBody Comment newComment) {
        Comment comment = commentService.gerComment(newComment.getId());
        comment.setContent(newComment.getContent());
        commentService.createComment(comment);
        return ResponseEntity.ok(comment);
    }
}
