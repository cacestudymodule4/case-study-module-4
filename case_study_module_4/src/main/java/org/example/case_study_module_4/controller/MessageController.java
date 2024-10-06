package org.example.case_study_module_4.controller;

import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FriendshipService;
import org.example.case_study_module_4.service.MessageService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {
    private final FriendshipService friendshipService;
    private final UserService userService;
    private final MessageService messageService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public MessageController(FriendshipService friendshipService,
                             UserService userService,
                             MessageService messageService) {
        this.friendshipService = friendshipService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/message")
    public String message(Principal principal, Model model) {
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        List<User> friends = friendshipService.getFriends(user);
        model.addAttribute("friends", friends);
        return "message";
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message, Principal principal) {
        System.out.println(principal.getName());
        System.out.println(message);
        System.out.println("ok1");
        User user = userService.findUserById(message.getReceiver().getId());
        System.out.println(user);
        String destination = "/topic/messages/" + user.getUsername();
        System.out.println(destination);
        Message newMessage = messageService.saveMessage(message);
        System.out.println(newMessage);
        System.out.println("ok2");
        messagingTemplate.convertAndSend(destination, newMessage);
        System.out.println("ok3");
    }

    @PostMapping("/history")
    public ResponseEntity<List<Message>> getHistory(@RequestBody Message message) {
        List<Long> ids = new ArrayList<>();
        ids.add(message.getSender().getId());
        ids.add(message.getReceiver().getId());
        return ResponseEntity.ok(messageService.getMessages(ids));
    }
}
