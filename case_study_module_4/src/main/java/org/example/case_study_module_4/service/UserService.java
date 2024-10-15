package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    void save(User user);

    List<User> findTop4User();

    User findUserById(Long id);

    List<User> findByFullName(String fullName);

    List<User> findAllByDeletedIsFalse();

    void deleteById(Long id);

}
