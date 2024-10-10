package org.example.case_study_module_4.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.case_study_module_4.model.Post;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Post post;
    private String content;
}
