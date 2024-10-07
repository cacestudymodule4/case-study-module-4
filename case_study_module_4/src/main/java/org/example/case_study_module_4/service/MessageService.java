package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessages(List<Long> ids);

    Message saveMessage(Message message);
}
