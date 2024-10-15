package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Role;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles(User user) {
        return roleRepository.findByUser(user);
    }
}
