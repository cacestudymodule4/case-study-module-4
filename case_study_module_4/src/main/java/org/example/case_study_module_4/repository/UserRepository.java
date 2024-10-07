package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC LIMIT 4")
    List<User> findTop4User();
}
