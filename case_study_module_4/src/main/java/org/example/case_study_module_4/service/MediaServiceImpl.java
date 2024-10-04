package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final PostService postService;

    public MediaServiceImpl(MediaRepository mediaRepository, PostService postService) {
        this.mediaRepository = mediaRepository;
        this.postService = postService;
    }

    @Override
    public List<Media> findAll(User user) {
        List<Media> result = new ArrayList<>();
        List<Post> posts = postService.findByUserId(user);
        for (Post p : posts) {
            List<Media> media = mediaRepository.findByPostId(p.getId());
            result.addAll(media);
        }
        return result;
    }
}
