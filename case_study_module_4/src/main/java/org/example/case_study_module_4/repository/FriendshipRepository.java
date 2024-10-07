package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Friendship;
import org.example.case_study_module_4.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    @Query("SELECT f.friend FROM Friendship f WHERE f.user.id = :userId AND f.status = :status")
    List<User> findFriendsByUserId(@Param("userId") Long userId, @Param("status") String status);

    @Query("SELECT f.user FROM Friendship f WHERE f.friend.id = :friendId AND f.status = :status")
    List<User> findFriendsByFriendId(@Param("friendId") Long friendId, @Param("status") String status);
}
