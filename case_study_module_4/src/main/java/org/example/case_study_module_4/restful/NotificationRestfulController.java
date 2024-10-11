package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationRestfulController {

    @Autowired
    private NotificationService notificationService;

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable("id") Long id,@RequestParam("isRead") Boolean isRead) {
        Notification notification = notificationService.findNotificationById(id);
        notification.setRead(isRead);
        notificationService.saveNotification(notification);
        return ResponseEntity.ok().build();
    }
}
