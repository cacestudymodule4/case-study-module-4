package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void sendCommentNotification(User sender, Post post) {
        Notification notification = new Notification();
        notification.setMessage(sender.getUsername() + " comment your post.");
        notification.setRecipient(post.getUser());
        notification.setSender(sender);
        notification.setPost(post);
        notificationRepository.save(notification);
    }

    @Override
    public void sendFollowNotification(User follower, User followee) {
        Notification notification = new Notification();
        notification.setMessage(follower.getUsername() + "has followed you.");
        notification.setRecipient(followee);
        notification.setSender(follower);
        notification.setPost(null);
        notificationRepository.save(notification);
    }

    @Override
    public void sendFriendNotification(User user, User otherUser) {
        Notification notification = new Notification();
        notification.setMessage(user.getUsername() + " asking to be friendship.");
        notification.setRecipient(otherUser);
        notification.setSender(user);
        notification.setPost(null);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findAllByRecipientId(Long recipientId) {
        return notificationRepository.findAllByRecipientId(recipientId);
    }

    @Override
    public Notification findNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId).orElse(null);
    }

    @Override
    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void saveNotifications(List<Notification> notifications) {
        notificationRepository.saveAll(notifications);
    }

    @Override
    public List<Notification> findNotificationsByRecipientIdIsRead(Long recipientId) {
        return notificationRepository.findAllByRecipientIdIsRead(recipientId);
    }
}
