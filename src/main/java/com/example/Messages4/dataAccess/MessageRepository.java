package com.example.Messages4.dataAccess;

import com.example.Messages4.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
