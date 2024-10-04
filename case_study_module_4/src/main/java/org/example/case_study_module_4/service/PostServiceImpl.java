package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final FollowService followService;

    public PostServiceImpl(PostRepository postRepository, FollowService followService) {
        this.postRepository = postRepository;
        this.followService = followService;
    }

    @Override
    public List<Post> findByUserId(User user) {
        List<Post> result = new ArrayList<>();
        List<User> followee = followService.findFolloweeByFollower(user.getId());
        for (User u : followee) {
            List<Post> posts = postRepository.findByUserId(u.getId());
            result.addAll(posts);
        }
        List<Post> posts = postRepository.findByUserId(user.getId());
        result.addAll(posts);
        result.sort(Comparator.comparing(Post::getCreatedAt).reversed());
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), result.size());
        Page<Post> pageResult = new PageImpl<>(result.subList(start, end), pageable, result.size());
        return result;
    }

}
