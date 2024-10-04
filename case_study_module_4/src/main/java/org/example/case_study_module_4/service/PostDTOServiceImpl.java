package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDTOServiceImpl implements PostDTOService {
    private final PostService postService;
    private final MediaRepository mediaRepository;

    public PostDTOServiceImpl(PostService postService, MediaRepository mediaRepository) {
        this.postService = postService;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public List<PostDTO> getPostDTOs(User user) {
        List<Post> posts = postService.findByUserId(user);
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post p : posts) {
            List<Media> mediaList = mediaRepository.findByPostId(p.getId());
            postDTOs.add(new PostDTO(mediaList, p, p.getUser()));
        }
        return postDTOs;
    }
}
