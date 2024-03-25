package com.mg.warning.service;

import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(Person person) {
        personRepository.update(person);
    }

    public void delete(String firstname, String lastname) {
        personRepository.delete(firstname, lastname);
    }

    public List<Person> findByCity(String city) {
        return personRepository.findByCity(city);
    }

    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress(address);
    }

    public List<Person> findByFirstAndLastName(String firstname, String lastname) {
        return personRepository.findByFirstAndLastName(firstname, lastname);
    }
}
