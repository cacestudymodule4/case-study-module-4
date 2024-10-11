package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.CustomOAuth2User;
import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        User user = userService.findUserByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setFullName(name);
            user.setProfilePic("https://bizweb.dktcdn.net/100/363/455/articles/aa764fc66c05b85be114-3fa80f6c-ec4d-4d16-9784-eb3f250ca551.jpg?v=1697101218753");
            String username = user.getEmail().replaceAll("@.*", "");
            user.setUsername(username);
            user.setPassword("defaultPassword");
            userService.save(user);
            Role role = new Role();
            role.setUser(user);
            role.setRole("USER");
            roleService.saveRole(role);
        }
        List<Role> roles = roleService.getRoles(user);
        return new CustomOAuth2User(user, oAuth2User.getAttributes(), roles);
    }
}

