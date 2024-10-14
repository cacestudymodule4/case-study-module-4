package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;
    private final PostDTOService postDTOService;
    private final MessageService messageService;
    private final NotificationService notificationService;

    public HomeController(UserService userService,
                          PostDTOService postDTOService,
                          MessageService messageService,
                          NotificationService notificationService) {
        this.userService = userService;
        this.postDTOService = postDTOService;
        this.messageService = messageService;
        this.notificationService = notificationService;
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        model.addAttribute("user", user);
        List<User> top4User = userService.findTop4User();
        model.addAttribute("top4User", top4User);
        List<PostDTO> postDTOS = postDTOService.getPostDTOs(user);
        model.addAttribute("postDTOS", postDTOS);
        List<Message> messages = messageService.getMessagesByReceiver(user);
        model.addAttribute("newMessages", messages.size());
        List<Notification> notifications = notificationService.findNotificationsByRecipientIdIsRead(user.getId());
        model.addAttribute("newNotify", notifications.size());
        return "home";
    }
}
