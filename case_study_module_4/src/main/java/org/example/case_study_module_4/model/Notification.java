package org.example.case_study_module_4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
    @Column(nullable = false)
    private boolean isRead = false;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notification_recipient"))
    private User recipient;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notification_sender"))
    private User sender;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_notification_post"))
    private Post post;
}

