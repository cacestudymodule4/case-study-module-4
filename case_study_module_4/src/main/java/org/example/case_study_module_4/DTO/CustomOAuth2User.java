package org.example.case_study_module_4.DTO;

import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User, UserDetails {
    private final User user;
    private final Map<String, Object> attributes;
    private final List<GrantedAuthority> authorities;

    public CustomOAuth2User(User user, Map<String, Object> attributes, List<Role> roles) {
        this.user = user;
        this.attributes = attributes;
        authorities = new ArrayList<>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                authorities.add(authority);
            }
        }
    }

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Có thể không cần mật khẩu cho OAuth2
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

