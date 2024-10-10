package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.DTO.CommentRequest;
import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.CommentService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
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

    public CommentRestfulController(CommentService commentService,
                                    UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setUser(user);
        comment.setPost(commentRequest.getPost());
        comment = commentService.createComment(comment);
        return ResponseEntity.ok(comment);
    }
}
