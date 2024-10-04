package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "re-password", defaultValue = "") String rePassword, RedirectAttributes redirectAttributes) {
        if (rePassword.isEmpty() || !rePassword.equals(user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Please re-enter your password");
        }
        try {
            registerService.registerUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
        return "redirect:/register";
    }
}
