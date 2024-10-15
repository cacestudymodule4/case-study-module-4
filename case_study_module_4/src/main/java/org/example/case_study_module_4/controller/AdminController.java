package org.example.case_study_module_4.controller;

import lombok.RequiredArgsConstructor;
import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.CommentService;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> postList = postService.findAllByDeletedIsFalse();
        model.addAttribute("postList", postList);
        List<User> userList = userService.findAllByDeletedIsFalse();
        model.addAttribute("userList", userList);
        List<Comment> commentList = commentService.findAll();
        model.addAttribute("commentList", commentList);
        return "homeAdmin";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        post.setDeleted(true);
        postService.save(post);
        return "redirect:/admin";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setDeleted(true);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "") String error, Model model) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful() {
        return "redirect:/homeAdmin";
    }

}
