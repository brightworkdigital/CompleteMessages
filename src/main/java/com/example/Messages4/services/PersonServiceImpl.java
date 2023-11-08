package com.example.Messages4.services;

import com.example.Messages4.dataAccess.PersonRepository;
import com.example.Messages4.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService{

    PersonRepository personRepository;
    @Override
    public Iterable<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name).orElseThrow( () -> new RuntimeException("PersonNotFound"));
    }

    @Override
    public Person findByEmail(String email) {
       Optional<Person> prospectivePerson = personRepository.findByEmail(email);
       if(prospectivePerson.isEmpty()) {
           return null;
       }
       else {
           return prospectivePerson.get();
       }
    }

    @Override
    public Person savePerson(Person person) {
        String name = person.getName();
        Optional<Person>  prospectivePerson = personRepository.findByName(name);
        if(prospectivePerson.isEmpty())  {
             return personRepository.save(person);
        }
        return prospectivePerson.get();
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
