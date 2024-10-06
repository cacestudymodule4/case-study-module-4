package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessages(List<Long> ids) {
        List<Message> messages = messageRepository.findMessagesBySenderIds(ids);
        messages.sort(Comparator.comparing(Message::getCreatedAt));
        return messages;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
