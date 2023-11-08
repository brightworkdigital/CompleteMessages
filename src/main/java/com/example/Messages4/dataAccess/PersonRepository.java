package com.example.Messages4.dataAccess;

import com.example.Messages4.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByName(String name);

    Optional<Person> findByEmail(String email);
}
