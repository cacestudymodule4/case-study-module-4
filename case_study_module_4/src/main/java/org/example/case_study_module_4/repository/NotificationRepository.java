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

    @Query("SELECT n FROM Notification n WHERE n.recipient.id = :recipientId ORDER BY n.createdAt DESC")
    List<Notification> findAllByRecipientId(@Param("recipientId") Long recipientId);

    @Query("SELECT n FROM Notification n WHERE n.recipient.id = :recipientId AND n.isRead = FALSE")
    List<Notification> findAllByRecipientIdIsRead(@Param("recipientId") Long recipientId);
}
