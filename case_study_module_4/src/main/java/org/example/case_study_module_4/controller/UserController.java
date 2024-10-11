package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.UserDTO;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDTOService userDTOService;
    private final FileStorageService fileStorageService;

    public UserController(UserService userService,
                          UserDTOService userDTOService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.userDTOService = userDTOService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") Long id, Principal principal, Model model) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        User otherUsers = userService.findUserById(id);
        if (user.getId() == otherUsers.getId()) {
            model.addAttribute("isUser", true);
        } else {
            model.addAttribute("isUser", false);
        }
        UserDTO userDTO = userDTOService.getUserDTO(otherUsers);
        if (!userDTO.getPosts().isEmpty()) {
            model.addAttribute("isPost", true);
        } else {
            model.addAttribute("isPost", false);
        }
        model.addAttribute("user", user);
        model.addAttribute("userDetail", userDTO);
        return "profile";
    }

    @GetMapping("/user/edit-profile")
    public ModelAndView editProfile(Principal principal, Model model) {
        ModelAndView modelAndView = new ModelAndView("profile_detail");
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        UserDTO userDTO = userDTOService.getUserDTO(user);
        model.addAttribute("user", userDTO);
        return modelAndView;
    }

    @GetMapping("/user/change-password")
    public ModelAndView changePassword() {
        ModelAndView modelAndView = new ModelAndView("change_password");
        return modelAndView;
    }
}
