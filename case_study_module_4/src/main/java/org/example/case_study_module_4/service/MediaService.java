package org.example.case_study_module_4.service;

import org.example.case_study_module_4.model.Media;
import org.example.case_study_module_4.model.User;

import java.util.List;

public interface MediaService {
    List<Media> findAll(User user);
}
