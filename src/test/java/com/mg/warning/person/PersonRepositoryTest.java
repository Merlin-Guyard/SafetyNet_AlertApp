package com.mg.warning.person;

import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @InjectMocks
    private PersonRepository personRepository = new PersonRepository();

    @Test
    void testFindAll() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findAll());
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testUpdate() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        Person personToModify = new Person();
        personToModify.setFirstName("Bobby");
        personToModify.setLastName("Dupont");
        personToModify.setZip(999);
        personRepository.update(personToModify);

        List<Person> persons = new ArrayList<>(personRepository.findAll());
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToModify.getZip());
    }

    @Test
    void testUpdateNoMatch() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        Person personToModify = new Person();
        personToModify.setFirstName("Sarah");
        personToModify.setLastName("Dupont");
        personToModify.setZip(999);
        personRepository.update(personToModify);

        List<Person> persons = new ArrayList<>(personRepository.findAll());
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testDelete() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);
        personRepository.delete("Bobby", "Dupont");

        List<Person> persons = new ArrayList<>(personRepository.findAll());
        assertThat(persons.isEmpty()).isTrue();
    }

    @Test
    void testDeleteNoMatch() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);
        personRepository.delete("Sarah", "Dupont");

        List<Person> persons = new ArrayList<>(personRepository.findAll());
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByFirstAndLastName() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName("Bobby", "Dupont"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByFirstAndLastNameNoMatch() {

        Person personToAdd = new Person();
        personToAdd.setFirstName("Bobby");
        personToAdd.setLastName("Dupont");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName("Sarah", "Dupont"));
        assertThat(persons.isEmpty()).isTrue();
    }

    @Test
    void testFindByAddress() {

        Person personToAdd = new Person();
        personToAdd.setAddress("Strawberry road");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByAddress("Strawberry road"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByAddressNoMatch() {

        Person personToAdd = new Person();
        personToAdd.setAddress("Strawberry road");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByAddress("Bad Road"));
        assertThat(persons.isEmpty()).isTrue();
    }


    @Test
    void testFindByCity() {

        Person personToAdd = new Person();
        personToAdd.setCity("Paris");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByCity("Paris"));
        Person personToCheck = new Person();
        personToCheck = persons.get(0);
        assertThat(personToCheck.getZip()).isEqualTo(personToAdd.getZip());
    }

    @Test
    void testFindByCityNoMatch() {

        Person personToAdd = new Person();
        personToAdd.setCity("Paris");
        personToAdd.setZip(42);
        personRepository.save(personToAdd);

        List<Person> persons = new ArrayList<>(personRepository.findByCity("Bad City"));
        assertThat(persons.isEmpty()).isTrue();
    }

}