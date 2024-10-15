package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.RegisterService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private final RegisterService registerService;
    private final UserService userService;

    public RegisterController(RegisterService registerService,
                              UserService userService) {
        this.registerService = registerService;
        this.userService = userService;
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
        boolean error = false;
        if (rePassword.isEmpty() || !rePassword.equals(user.getPassword())) {
            redirectAttributes.addFlashAttribute("passwordError", "Mật khẩu không chính xác");
            error = true;
        }
        if (user.getEmail().isEmpty() || !user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            redirectAttributes.addFlashAttribute("emailError", "Email không đúng định dạng example@example.com");
            error = true;
        }
        if (userService.findUserByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailError", "Email đã có người sử dụng");
            error = true;
        }
        if (error) {
            String password = user.getPassword();
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("password", password);
            return "redirect:/register";
        } else {
            registerService.registerUser(user);
            redirectAttributes.addFlashAttribute("notify", "Đăng kí thành công");
            return "redirect:/login";
        }
    }
}
