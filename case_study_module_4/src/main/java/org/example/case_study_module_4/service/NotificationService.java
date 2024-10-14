package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Notification;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface NotificationService {
    void sendLikeNotification(User user, Post post);

    void sendCommentNotification(User user, Post post);

    void sendFollowNotification(User follower, User followee);

    void sendFriendNotification(User user, User otherUser);

    List<Notification> findAllByRecipientId(Long recipientId);

    Notification findNotificationById(Long notificationId);

    void saveNotification(Notification notification);

    void saveNotifications(List<Notification> notifications);

    List<Notification> findNotificationsByRecipientIdIsRead(Long recipientId);
}
