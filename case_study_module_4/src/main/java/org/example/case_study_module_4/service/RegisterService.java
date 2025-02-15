package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.RoleRepository;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private RegisterService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setProfilePic("https://bizweb.dktcdn.net/100/363/455/articles/aa764fc66c05b85be114-3fa80f6c-ec4d-4d16-9784-eb3f250ca551.jpg?v=1697101218753");
        User newUser = userRepository.save(user);
        Role role = new Role();
        role.setUser(newUser);
        role.setRole("USER");
        roleRepository.save(role);
    }
}
