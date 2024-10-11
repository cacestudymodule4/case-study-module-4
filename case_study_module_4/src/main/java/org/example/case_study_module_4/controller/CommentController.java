package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/delete/{commentId}/{postId}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable Long postId) {
        commentService.deleteComment(commentId);
        return "redirect:/post/view/" + postId;
    }
}
