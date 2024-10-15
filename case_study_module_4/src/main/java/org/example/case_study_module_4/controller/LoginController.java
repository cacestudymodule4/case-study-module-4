package org.example.case_study_module_4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "") String error, Model model) {
        if (!error.isEmpty()) {
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không đúng");
        }
        return "login";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful() {
        return "redirect:/login";
    }
}
