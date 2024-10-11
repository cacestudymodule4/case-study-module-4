package org.example.case_study_module_4.service;

import org.example.case_study_module_4.DTO.PostDTO;
import org.example.case_study_module_4.model.Comment;
import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.CommentRepository;
import org.example.case_study_module_4.repository.LikeRepository;
import org.example.case_study_module_4.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDTOServiceImpl implements PostDTOService {
    private final PostService postService;
    private final MediaRepository mediaRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public PostDTOServiceImpl(PostService postService,
                              MediaRepository mediaRepository,
                              LikeRepository likeRepository,
                              CommentRepository commentRepository) {
        this.postService = postService;
        this.mediaRepository = mediaRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<PostDTO> getPostDTOs(User user) {
        List<Post> posts = postService.findByUserId(user);
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post p : posts) {
            List<Media> mediaList = mediaRepository.findByPostId(p.getId());
            int likes = likeRepository.findByPostId(p.getId()).size();
            List<Comment> comments = commentRepository.findByPostId(p.getId());
            postDTOs.add(new PostDTO(mediaList, p, p.getUser(), likes, comments));
        }
        return postDTOs;
    }

    @Override
    public PostDTO getPostDTO(Post post) {
        List<Media> mediaList = mediaRepository.findByPostId(post.getId());
        int likes = likeRepository.findByPostId(post.getId()).size();
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        return new PostDTO(mediaList, post, post.getUser(), likes, comments);
    }
}
