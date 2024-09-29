package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
