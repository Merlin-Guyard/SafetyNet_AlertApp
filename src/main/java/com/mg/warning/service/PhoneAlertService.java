package com.mg.warning.service;

import com.mg.warning.dto.PhoneDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


@Service
public class PhoneAlertService {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private PersonService personService;

    public List<PhoneDTO> getPhoneDTO(int stationNumber) {

        //Get all persons from firestation's address
        List<Person> persons = getPersons(stationNumber);

        //Get and write phone numbers
        List<PhoneDTO> dtoPhoneList = getPhoneDTOS(persons);

        Logger.info("getPhoneDTO executed successfully");
        return dtoPhoneList;
    }

    private List<PhoneDTO> getPhoneDTOS(List<Person> persons) {
//        Logger.debug("getting persons");
        List<PhoneDTO> dtoPhoneList = new ArrayList<>();
        for (Person person : persons) {
            PhoneDTO dtoPhone = new PhoneDTO();
            dtoPhone.setPhone(person.getPhone());
            dtoPhoneList.add(dtoPhone);
        }
        return dtoPhoneList;
    }

    private List<Person> getPersons(int stationNumber) {
//        Logger.debug("getting and writing numbers");
        List<Person> persons = new ArrayList<>();
        List<Firestation> fireStations = firestationService.findByStationNumber(stationNumber);
        for (Firestation firestation : fireStations) {
            persons.addAll(personService.findByAddress(firestation.getAddress()));
        }
        return persons;
    }
}