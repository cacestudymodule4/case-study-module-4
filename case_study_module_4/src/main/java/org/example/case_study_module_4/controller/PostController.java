package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PostController {
    private final UserService userService;

    public PostController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String post(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "create_post";
    }
}
