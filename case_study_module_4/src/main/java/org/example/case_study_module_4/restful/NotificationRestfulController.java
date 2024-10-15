package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.NotificationService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationRestfulController {
    @Autowired
    private NotificationService notificationService;
    private final UserService userService;

    public NotificationRestfulController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable("id") Long id, @RequestParam("isRead") Boolean isRead) {
        Notification notification = notificationService.findNotificationById(id);
        notification.setRead(isRead);
        notificationService.saveNotification(notification);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/markAllRead")
    public ResponseEntity<Void> markAllRead(Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        List<Notification> notificationList = notificationService.findAllByRecipientId(user.getId());
        for (Notification notification : notificationList) {
            notification.setRead(true);
        }
        notificationService.saveNotifications(notificationList);
        return ResponseEntity.ok().build();
    }
}
