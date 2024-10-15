package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    List<Role> getRoles(User user);
}
