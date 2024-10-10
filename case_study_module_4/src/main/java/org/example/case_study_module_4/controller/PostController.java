package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.PostDTOService;
import org.example.case_study_module_4.service.PostService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {
    private final UserService userService;
    private final PostService postService;
    private final PostDTOService postDTOService;

    public PostController(UserService userService,
                          PostService postService,
                          PostDTOService postDTOService) {
        this.userService = userService;
        this.postService = postService;
        this.postDTOService = postDTOService;
    }

    @GetMapping("/create")
    public String post(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "create_post";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long postId, Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        Post post = postService.findPostById(postId);
        PostDTO postDTO = postDTOService.getPostDTO(post);
        model.addAttribute("post", postDTO);
        model.addAttribute("user", user);
        return "view_post";
    }
}
