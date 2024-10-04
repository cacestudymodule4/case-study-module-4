package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final PostDTOService postDTOService;

    public HomeController(UserService userService, PostDTOService postDTOService) {
        this.userService = userService;
        this.postDTOService = postDTOService;
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        List<User> top4User = userService.findTop4User();
        model.addAttribute("top4User", top4User);
        List<PostDTO> postDTOS = postDTOService.getPostDTOs(user);
        model.addAttribute("postDTOS", postDTOS);
        return "home";
    }
}
