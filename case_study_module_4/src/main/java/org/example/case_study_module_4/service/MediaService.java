package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Post;
import org.example.case_study_module_4.model.User;

import java.util.Map;

public interface MediaService {
    Map<Post, String> findAll(User user);
}
