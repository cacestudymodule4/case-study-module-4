package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ModelAndView notification(Principal principal) {
        ModelAndView mav = new ModelAndView("notification");
        User receiver;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            receiver = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            receiver = userService.findUserByEmail(principal.getName());
        }
        mav.addObject("user", receiver);
        List<Notification> listNoti = notificationService.findAllByRecipientId(receiver.getId());
        mav.addObject("listNoti", listNoti);
        return mav;
    }
}
