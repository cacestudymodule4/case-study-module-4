package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;

public interface NotificationService {
    void sendLikeNotification(User user, Post post);
}
