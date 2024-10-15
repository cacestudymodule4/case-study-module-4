package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC LIMIT 4")
    List<User> findTop4User();

    @Query("SELECT u FROM User u WHERE u.fullName LIKE :fullName ORDER BY u.createdAt")
    List<User> findByFullName(@Param("fullName") String fullName);

    @Query("SELECT u FROM  User u WHERE u.isDeleted = FALSE ")
    List<User>findAllByDeletedIsFalse();
}
