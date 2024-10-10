package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendLikeNotification(User sender, Post post) {
        Notification notification = new Notification();
        notification.setMessage(sender.getUsername() + " liked your post.");
        notification.setRecipient(post.getUser());
        notification.setSender(sender);
        notification.setPost(post);
        notificationRepository.save(notification);
    }
}
