package org.example.case_study_module_4.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.case_study_module_4.model.Post;

import java.sql.Timestamp;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private String bio;
    private String profilePic;
    private Timestamp createdAt;
    private Map<Post, String> posts;
    private int countFollower;
    private int countFollowee;
    private int countFriends;
}
