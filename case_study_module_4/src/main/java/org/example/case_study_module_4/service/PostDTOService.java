package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface PostDTOService {
    List<PostDTO> getPostDTOs(User user);

    PostDTO getPostDTO(Post post);
}
