package com.mg.warning.service;

import com.mg.warning.dto.FirestationDTO;
import com.mg.warning.dto.FirestationWithNbDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationAlertService {

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;


    public FirestationWithNbDTO getFirestationAlertDTOWithSum(int stationNumber) {

        FirestationWithNbDTO dtoWithSum = new FirestationWithNbDTO();

        //Get persons from firestation's address
        List<Person> persons = getPersons(stationNumber);

        //Get only interested data
        List<FirestationDTO> dtoFirestationList = getFirestationDTOS(persons);

        //Get medicalrecords from persons
        List<MedicalRecord> medicalRecords = getMedicalRecords(persons);

        //Check if minor or major using age
        Logger.debug("check age status");
        int adult = 0;
        int children = 0;
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) >= 18) {
                adult++;
            } else if (medicalRecord.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) < 18) {
                children++;
            }
        }

        dtoWithSum.setFirestationAlertDTOS(dtoFirestationList);
        dtoWithSum.setNbAdults(adult);
        dtoWithSum.setNbChildrens(children);

        Logger.info("getFirestationAlertDTOWithSum executed successfully");
        return dtoWithSum;
    }

    private List<MedicalRecord> getMedicalRecords(List<Person> persons) {
        Logger.debug("find medicalrecords from persons");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        for (Person person : persons) {
            medicalRecords.add(medicalRecordService.findByName(person.getFirstName(), person.getLastName()));
        }
        return medicalRecords;
    }

    private List<FirestationDTO> getFirestationDTOS(List<Person> persons) {
        Logger.debug("get persons");
        List<FirestationDTO> dtoFirestationList = new ArrayList<>();
        for (Person person : persons) {
            FirestationDTO dtoFirestation = new FirestationDTO();
            dtoFirestation.setFirstname(person.getFirstName());
            dtoFirestation.setLastname(person.getLastName());
            dtoFirestation.setAddress(person.getAddress());
            dtoFirestation.setPhone(person.getPhone());
            dtoFirestationList.add(dtoFirestation);
        }
        return dtoFirestationList;
    }

    private List<Person> getPersons(int stationNumber) {
        Logger.debug("find persons from firestation");
        List<Firestation> fireStations = firestationService.findByStationNumber(stationNumber);
        List<Person> persons = new ArrayList<>();
        for (Firestation firestation : fireStations) {
            persons.addAll(personService.findByAddress(firestation.getAddress()));
        }
        return persons;
    }
}
