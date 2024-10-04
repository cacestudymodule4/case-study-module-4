package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Friendship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Integer> {
}
