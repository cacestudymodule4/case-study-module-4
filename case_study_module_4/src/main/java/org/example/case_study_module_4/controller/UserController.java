package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.UserDTO;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDTOService userDTOService;

    public UserController(UserService userService,
                          UserDTOService userDTOService) {
        this.userService = userService;
        this.userDTOService = userDTOService;
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") Long id, Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        User otherUsers = userService.findUserById(id);
        if (user.getId() == otherUsers.getId()) {
            model.addAttribute("isUser", true);
        } else {
            model.addAttribute("isUser", false);
        }
        UserDTO userDTO = userDTOService.getUserDTO(otherUsers);
        model.addAttribute("user", user);
        model.addAttribute("userDetail", userDTO);
        return "profile";
    }
}
