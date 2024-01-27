package com.example.Messages4.dataAccess;

import com.example.Messages4.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Iterable<Message> findBySenderEmail(String email);
}
