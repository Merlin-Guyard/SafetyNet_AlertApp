package com.mg.warning.service;

import com.mg.warning.dto.FloodDTO;
import com.mg.warning.dto.FloodPersonsDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodAlertService {

    @Autowired
    private PersonService personService;

    @Autowired
    private FirestationService firestationService;

    @Autowired
    private MedicalRecordService medicalRecordService;


    public List<FloodDTO> getFloodDTO(int[] stationNumber) {

        //get firestation from station number
        List<Firestation> fireStations = getFirestations(stationNumber);

        //get and write persons and medicalrecords
        List<FloodDTO> dtoFloodList = getFloodDTOS(fireStations);

        Logger.info("getFloodDTO executed successfully");
        return dtoFloodList;
    }

    private List<Firestation> getFirestations(int[] stationNumber) {
        Logger.debug("find firestations");
        List<Firestation> fireStations = new ArrayList<>();
        for (int i : stationNumber) {
            fireStations.addAll(firestationService.findByStationNumber(i));
        }
        return fireStations;
    }

    private List<FloodDTO> getFloodDTOS(List<Firestation> fireStations) {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        List<Person> persons = new ArrayList<>();
        List<FloodDTO> dtoFloodList = new ArrayList<>();
        Logger.debug("get and write persons and medicalrecords");
        for (Firestation firestation : fireStations) {
            FloodDTO dtoFlood = new FloodDTO();
            List<FloodPersonsDTO> dtoFloodPersonList = new ArrayList<>();
            persons.addAll(personService.findByAddress(firestation.getAddress()));

            for (Person person : persons) {
                FloodPersonsDTO dtoFloodPerson = new FloodPersonsDTO();
                dtoFloodPerson.setLastName(person.getLastName());
                dtoFloodPerson.setPhone(person.getPhone());
                for (MedicalRecord medicalRecord : medicalRecords) {
                    if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                        dtoFloodPerson.setAge(medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                        dtoFloodPerson.setMedications(medicalRecord.getMedications());
                        dtoFloodPerson.setAllergies(medicalRecord.getAllergies());
                        dtoFloodPersonList.add(dtoFloodPerson);
                    }
                }
            }

            dtoFlood.setAddress(firestation.getAddress());
            dtoFlood.setFloodAlertPersonsDTO(dtoFloodPersonList);
            dtoFloodList.add(dtoFlood);
            persons.clear();
        }
        return dtoFloodList;
    }

}
