package org.example.case_study_module_4.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private List<Media> mediaList;
    private Post post;
    private User user;
    private int like;
    private List<Comment> commentList;
}
