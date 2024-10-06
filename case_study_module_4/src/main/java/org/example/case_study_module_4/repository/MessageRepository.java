package org.example.case_study_module_4.repository;

import org.example.case_study_module_4.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.sender.id IN :senderIds")
    List<Message> findMessagesBySenderIds(@Param("senderIds") List<Long> senderIds);
}
