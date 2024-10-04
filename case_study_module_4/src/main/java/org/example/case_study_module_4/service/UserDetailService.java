package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.UserDetail;
import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.RoleRepository;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository rollRepository;

    public UserDetailService(UserRepository userRepository, RoleRepository rollRepository) {
        this.userRepository = userRepository;
        this.rollRepository = rollRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> userRole = rollRepository.findByUser(user);
        return new UserDetail(user, userRole);
    }
}
