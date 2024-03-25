package com.mg.warning.service;

import com.mg.warning.dto.ChildrenDTO;
import com.mg.warning.dto.ChildrenWithFamilyDTO;
import com.mg.warning.dto.FamilyDTO;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChildAlertService {

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public ChildrenWithFamilyDTO getChildrenWithFamilyDTO(String address) {

        //Check by address then age
        Logger.debug("sort by address and ages then get family and children");
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Person> persons = personService.findAll();
        List<ChildrenDTO> dtoChildrenList = new ArrayList<>();
        List<FamilyDTO> dtoFamilyList = new ArrayList<>();
        int personAge;
        for (Person person : persons) {
            if (person.getAddress().equals(address)) {
                personAge = medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName());

                if (personAge >= 18) {
                    FamilyDTO dtoFamily = new FamilyDTO();
                    dtoFamily.setFirstname(person.getFirstName());
                    dtoFamily.setLastname(person.getLastName());
                    dtoFamilyList.add(dtoFamily);
                } else {
                    ChildrenDTO dtoChildren = new ChildrenDTO();
                    dtoChildren.setFirstname(person.getFirstName());
                    dtoChildren.setLastname(person.getLastName());
                    dtoChildren.setAge(personAge);
                    dtoChildrenList.add(dtoChildren);
                }
            }
        }

        //Check if a children were found otherwise return a blank list
        ChildrenWithFamilyDTO dtoChildrenAndFamily = getChildrenWithFamilyDTO(dtoChildrenList, dtoFamilyList);

        Logger.info("getChildrenWithFamilyDTO executed successfully");
        return dtoChildrenAndFamily;
    }

    private ChildrenWithFamilyDTO getChildrenWithFamilyDTO(List<ChildrenDTO> dtoChildrenList, List<FamilyDTO> dtoFamilyList) {
        Logger.debug("check if children found");
        ChildrenWithFamilyDTO dtoChildrenAndFamily = new ChildrenWithFamilyDTO();
        if (dtoChildrenList.size() > 0) {
            dtoChildrenAndFamily.setFamily(dtoFamilyList);
            dtoChildrenAndFamily.setChildren(dtoChildrenList);
        }
        return dtoChildrenAndFamily;
    }
}
