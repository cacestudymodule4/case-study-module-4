package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
