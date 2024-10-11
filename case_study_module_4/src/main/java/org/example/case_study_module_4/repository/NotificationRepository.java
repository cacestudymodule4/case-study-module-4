package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.post.id = :postId")
    List<Notification> findByPostId(@Param("postId") Long postId);

    List<Notification> findAllByRecipientId(long recipientId);
}
