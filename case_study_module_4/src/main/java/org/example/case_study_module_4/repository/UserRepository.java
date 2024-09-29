package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
