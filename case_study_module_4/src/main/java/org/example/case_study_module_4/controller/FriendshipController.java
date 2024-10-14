package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.DTO.FriendshipDTO;
import org.example.case_study_module_4.model.Friendship;
import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FriendshipService;
import org.example.case_study_module_4.service.MessageService;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/friendship")
public class FriendshipController {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final MessageService messageService;
    private final NotificationService notificationService;

    public FriendshipController(UserService userService,
                                FriendshipService friendshipService,
                                MessageService messageService,
                                NotificationService notificationService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.messageService = messageService;
        this.notificationService = notificationService;
    }

    @GetMapping("/search")
    public String search(Principal principal, Model model, @RequestParam(value = "q", defaultValue = "") String search) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        model.addAttribute("user", user);
        List<User> userList = userService.findByFullName(search);
        List<FriendshipDTO> friendshipDTOS = new ArrayList<>();
        for (User u : userList) {
            FriendshipDTO friendshipDTO = new FriendshipDTO();
            friendshipDTO.setUser(user);
            friendshipDTO.setOtherUser(u);
            String status = friendshipService.getStatus(user, u);
            if (status.equals("pending")) {
                Friendship friendship = friendshipService.getFriendship(user, u);
                if (friendship.getUser() != user) {
                    status = "received";
                }
            }
            if (Objects.equals(user.getId(), u.getId())) {
                status = "isUser";
            }
            friendshipDTO.setStatus(status);
            friendshipDTOS.add(friendshipDTO);
        }
        model.addAttribute("friendshipDTOS", friendshipDTOS);
        List<Message> messages = messageService.getMessagesByReceiver(user);
        model.addAttribute("newMessages", messages.size());
        List<Notification> notifications = notificationService.findNotificationsByRecipientIdIsRead(user.getId());
        model.addAttribute("newNotify", notifications.size());
        return "add_friend";
    }
}
