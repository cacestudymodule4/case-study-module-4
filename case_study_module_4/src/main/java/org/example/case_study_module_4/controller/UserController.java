package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> user(@RequestParam(value = "id", defaultValue = "") Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }
}
