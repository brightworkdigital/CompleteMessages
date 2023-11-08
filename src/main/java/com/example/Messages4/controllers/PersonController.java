package com.example.Messages4.controllers;

import com.example.Messages4.model.Person;
import com.example.Messages4.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@CrossOrigin public class PersonController {

    IPersonService personService;

    @GetMapping("/persons")
    public Iterable<Person> getAllPeople()  {
        return personService.getAllPeople();
    }

    @GetMapping("/persons/{name}")
    public Person getPersonByName(@PathVariable String name) {
       return personService.findByName(name);
    }

    @GetMapping("/getByEmail/{email}")
    public Person getPersonByEmail(@PathVariable String email) {
        return personService.findByEmail(email);
    }

    @PostMapping("/addPerson")
    public Person createPerson(@RequestBody Person person) throws URISyntaxException {
         return personService.savePerson(person);
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }
}
