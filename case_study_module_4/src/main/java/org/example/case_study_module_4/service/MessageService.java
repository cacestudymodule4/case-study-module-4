package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(List<Long> ids);

    Message saveMessage(Message message);

    List<Message> getMessagesByStatus(User sender, User receiver);

    void saveMessages(List<Message> messages);

    List<Message> getMessagesByReceiver(User receiver);
}
