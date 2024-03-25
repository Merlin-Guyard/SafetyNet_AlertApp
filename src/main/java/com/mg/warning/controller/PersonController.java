package com.mg.warning.controller;

import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/GetAll")
    public List<Person> getAllPersons() {
        Logger.info("/person function get all called");
        return personService.findAll();
    }

    @PostMapping(value = "")
    public void postOnePerson(@RequestBody Person person) {
        Logger.info("/person function post called for {}, {}", person.getFirstName(), person.getLastName());
        personService.save(person);
    }

    @PutMapping(value = "")
    public void updateOnePerson(@RequestBody Person person) {
        Logger.info("/person function update called for {}, {}", person.getFirstName(), person.getLastName());
        personService.update(person);
    }

    @DeleteMapping(value = "/{firstname}/{lastname}")
    public void delOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        Logger.info("/person function delete called for {}, {}", firstname, lastname);
        personService.delete(firstname, lastname);
    }
}