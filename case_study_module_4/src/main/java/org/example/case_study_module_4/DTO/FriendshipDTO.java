package org.example.case_study_module_4.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.case_study_module_4.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDTO {
    private User user;
    private User otherUser;
    private String status;
}
