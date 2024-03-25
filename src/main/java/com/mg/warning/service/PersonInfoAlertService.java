package com.mg.warning.service;

import com.mg.warning.dto.PersonInfoDTO;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoAlertService {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public List<PersonInfoDTO> getPersonInfoDTO(String firstname, String lastname) {

        //get and write infos
        List<PersonInfoDTO> dtoPersonInfoList = getPersonInfoDTOS(firstname, lastname);

        Logger.info("getPersonInfoDTO executed successfully");
        return dtoPersonInfoList;
    }


    private List<PersonInfoDTO> getPersonInfoDTOS(String firstname, String lastname) {
        List<Person> persons = new ArrayList<>(personService.findByFirstAndLastName(firstname, lastname));
        List<PersonInfoDTO> dtoPersonInfoList = new ArrayList<>();

        //get persons infos
        Logger.debug("get persons infos");
        for (Person person : persons) {
            PersonInfoDTO dtoPersonInfo = new PersonInfoDTO();
            dtoPersonInfo.setLastName(person.getLastName());
            dtoPersonInfo.setAddress(person.getAddress());
            dtoPersonInfo.setEmail(person.getEmail());

            //get medicalrecords infos
            extracted(firstname, lastname, dtoPersonInfoList, person, dtoPersonInfo);
        }
        return dtoPersonInfoList;
    }

    private void extracted(String firstname, String lastname, List<PersonInfoDTO> dtoPersonInfoList, Person person, PersonInfoDTO dtoPersonInfo) {
        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordService.findByFirstAndLastName(firstname, lastname));

        Logger.debug("get medicalrecords infos");
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                dtoPersonInfo.setAge(medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                dtoPersonInfo.setMedications(medicalRecord.getMedications());
                dtoPersonInfo.setAllergies(medicalRecord.getAllergies());
            }
            dtoPersonInfoList.add(dtoPersonInfo);
        }
    }
}
