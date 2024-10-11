package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.Message;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FriendshipService;
import org.example.case_study_module_4.service.MessageService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/message")
public class MessageRestfulController {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final MessageService messageService;

    public MessageRestfulController(UserService userService,
                                    FriendshipService friendshipService,
                                    MessageService messageService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> sendMessage(@RequestBody() User friend) {
        User user = userService.findUserById(friend.getId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/new-message")
    public ResponseEntity<List<User>> sendNewMessage(Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        List<User> friends = friendshipService.getFriends(user);
        List<User> users = new ArrayList<>();
        for (User u : friends) {
            if (!messageService.getMessagesByStatus(u, user).isEmpty()) {
                users.add(u);
            }
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/read-message")
    public ResponseEntity<Void> readMessages(@RequestBody User friend, Principal principal) {
        User user;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            user = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            user = userService.findUserByEmail(principal.getName());
        }
        List<Message> messages = messageService.getMessagesByStatus(friend, user);
        messageService.saveMessages(messages);
        return ResponseEntity.noContent().build();
    }
}
