package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    private final PostService postService;

    public MediaServiceImpl(MediaRepository mediaRepository, PostService postService) {
        this.mediaRepository = mediaRepository;
        this.postService = postService;
    }

    @Override
    public Map<Post, String> findAll(User user) {
        Map<Post, String> result = new TreeMap<>(Comparator.comparing(Post::getCreatedAt).reversed());
        List<Post> posts = postService.findPostsByUser(user);
        for (Post p : posts) {
            List<Media> media = mediaRepository.findByPostId(p.getId());
            if (!media.isEmpty()) {
                result.put(p, media.getFirst().getMediaUrl());
            }
        }
        return result;
    }
}
