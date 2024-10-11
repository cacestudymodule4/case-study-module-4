package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> findByUserId(User user);

    Map<Post, List<Media>> createPost(Post post, MultipartFile[] mediaFiles);

    List<Post> findPostsByUser(User user);

    Post findPostById(Long postId);

    void deletePostById(Long postId);
}
