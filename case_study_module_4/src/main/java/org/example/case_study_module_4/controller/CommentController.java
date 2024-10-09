package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/view/{postId}/{name}")
    public String getCommentsByPost(@PathVariable Long postId,@PathVariable String  name, Model model) {
        List<Comment> comments = commentService.findByPost(postId);
        model.addAttribute("comments", comments);
        model.addAttribute("postId", postId);
        model.addAttribute("namePost", name);
        model.addAttribute("comment", new Comment());
        System.out.println(name);
        return "detailPage";
    }


}

