package org.example.case_study_module_4.restful;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.service.FileStorageService;
import org.example.case_study_module_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestfulController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/update-avatar")
    public String updateUserAvatar(@RequestParam("profilePicture") MultipartFile profilePicture, Principal principal) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        String avatarUrl = fileStorageService.storeFile(profilePicture);
        userUpdate.setProfilePic(avatarUrl);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }

    @PostMapping("/update-info")
    public String updateUserInfo(Principal principal,@RequestParam("name") String name, @RequestParam("bio") String bio) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        userUpdate.setBio(bio);
        userUpdate.setFullName(name);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }

    @PostMapping("/change-password")
    public String changePassword(Principal principal, @RequestParam("password") String password) {
        User userUpdate;
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2User oAuth2User = ((OAuth2AuthenticationToken) principal).getPrincipal();
            userUpdate = userService.findUserByEmail(oAuth2User.getAttribute("email"));
        } else {
            userUpdate = userService.findUserByEmail(principal.getName());
        }
        userUpdate.setPassword(password);
        userService.save(userUpdate);
        return "redirect:/user/edit-profile";
    }
}
