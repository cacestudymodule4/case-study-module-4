package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Like;
import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;
import org.example.case_study_module_4.repository.LikeRepository;
import org.example.case_study_module_4.repository.PostRepository;
import org.example.case_study_module_4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void likePost(Long postId, Long userId) {
        // Tìm bài viết dựa vào postId
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        // Tìm người dùng dựa vào userId
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Kiểm tra nếu người dùng đã like bài viết
        Like existingLike = likeRepository.findByPostAndUser(post, user).orElse(null);
        if (existingLike != null) {
            // Nếu đã like thì thực hiện unlike (xóa lượt like)
            likeRepository.delete(existingLike);
        } else {
            // Nếu chưa like thì tạo lượt like mới
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
        }
    }

    @Override
    public Long countLikes(Long postId) {
        // Tìm bài viết dựa vào postId
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }

        // Đếm số lượt like dựa vào bài viết
        Long likeCount = likeRepository.countByPost(post);
        return likeCount;
    }
}
