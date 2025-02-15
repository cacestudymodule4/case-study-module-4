package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findTop4User() {
        return userRepository.findTop4User();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            fullName = "";
        }
        String str = "%" + fullName + "%";
        return userRepository.findByFullName(str);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllByDeletedIsFalse() {
        return userRepository.findAllByDeletedIsFalse();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
