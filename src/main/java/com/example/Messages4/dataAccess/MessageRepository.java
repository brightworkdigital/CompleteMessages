package com.example.Messages4.dataAccess;

import com.example.Messages4.model.Message;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
