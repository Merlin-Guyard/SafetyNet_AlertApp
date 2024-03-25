package com.mg.warning.repository;

import com.mg.warning.model.Person;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> personsList = new ArrayList<>();

    //GET
    public List<Person> findAll() {
        return personsList;
    }

    //GET BY FIRSTNAME & LASTNAME
    public List<Person> findByFirstAndLastName(String firstname, String lastname) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            Logger.debug("vérification de {}, {}", person.getFirstName(), person.getLastName());
            if (person.getFirstName().equals(firstname) && person.getLastName().equals(lastname)) {
                result.add(person);
                Logger.debug("personne ajoutée");
            }
        }
        return result;
    }

    //PUT
    public void update(Person person) {
        Logger.debug("suppression de {}, {}", person.getFirstName(), person.getLastName());
        int index = 0;
        List<Person> copy = new ArrayList<>(personsList);
        for (Person personLoop : copy) {
            /*if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                personsList.set(index, person);
                index++;
            }*/
            if (personLoop.getFirstName().equals(person.getFirstName()) && personLoop.getLastName().equals(person.getLastName())) {
                personsList.remove(personLoop);
                personsList.add(person);
                Logger.debug("personne mise à jour");
            }
        }
    }

//    //PUT V2
//    public void update2(Person person) {
//        personsList
//                .stream()
//                .filter(each -> each.equals(person))
//                .forEach(each -> {
//                    personsList.remove(each);
//                    personsList.add(person);
//                });
//    }

    //POST
    public void save(Person persons) {
        Logger.debug("ajout des personnes");
        personsList.add(persons);
    }

    //DELETE
    public void delete(String firstname, String lastname) {
        Logger.debug("suppression de {}, {}", firstname, lastname);
        personsList.removeIf(person -> person.getFirstName().equals(firstname) && person.getLastName().equals(lastname));
    }

    //GET BY ADDRESS
    public List<Person> findByAddress(String address) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            Logger.debug("vérification de {}, {}", person.getFirstName(), person.getLastName());
            if (person.getAddress().equals(address)) {
                result.add(person);
                Logger.debug("personne ajoutée");
            }
        }
        return result;
    }

    //GET BY City
    public List<Person> findByCity(String city) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            Logger.debug("vérification de {}, {}", person.getFirstName(), person.getLastName());
            if (person.getCity().equals(city)) {
                result.add(person);
                Logger.debug("personne ajoutée");
            }
        }
        return result;
    }
}
