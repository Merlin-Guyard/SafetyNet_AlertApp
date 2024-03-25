package com.mg.warning.alert.children;

import com.mg.warning.dto.ChildrenDTO;
import com.mg.warning.dto.ChildrenWithFamilyDTO;
import com.mg.warning.dto.FamilyDTO;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.ChildAlertService;
import com.mg.warning.service.MedicalRecordService;
import com.mg.warning.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ChildServiceTest {

        @Mock
        private PersonService personService = mock(PersonService.class);

        @Mock
        private MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

        @InjectMocks
        private ChildAlertService childAlertService = new ChildAlertService();

    @Test
    void TestChildMinor() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecordChild = new MedicalRecord();
        MedicalRecord medicalRecordAdult = new MedicalRecord();
        List<Person> persons = new ArrayList<>();
        Person personChild = new Person();
        Person personAdult = new Person();

        //Child
        medicalRecordChild.setFirstName("Bobby");
        medicalRecordChild.setLastName("Dupont");
        medicalRecordChild.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecordChild);

        //Adult
        medicalRecordAdult.setFirstName("Sarah");
        medicalRecordAdult.setLastName("Dupont");
        medicalRecordAdult.setBirthdate("03/06/2000");
        medicalRecords.add(medicalRecordAdult);


        when(medicalRecordService.findAll())
                .thenReturn(medicalRecords);

        personChild.setFirstName("Bobby");
        personChild.setLastName("Dupont");
        personChild.setAddress("TestRoad");
        persons.add(personChild);

        personAdult.setFirstName("Sarah");
        personAdult.setLastName("Dupont");
        personAdult.setAddress("TestRoad");
        persons.add(personAdult);
        when(personService.findAll())
                .thenReturn(persons);

        ChildrenWithFamilyDTO result = childAlertService.getChildrenWithFamilyDTO(personChild.getAddress());
        List<ChildrenDTO> resultChildren = result.getChildren();
        ChildrenDTO resultChild = resultChildren.get(0);
        List<FamilyDTO> resultAdults = result.getFamily();
        FamilyDTO resultAdult = resultAdults.get(0);

        assertThat(resultChild.getFirstname()).isEqualTo(personChild.getFirstName());
        assertThat(resultChild.getAge()).isEqualTo(17);

        assertThat(resultAdult.getFirstname()).isEqualTo(personAdult.getFirstName());
    }

    @Test
    void TestNoChildren() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecordChild = new MedicalRecord();
        MedicalRecord medicalRecordAdult = new MedicalRecord();
        List<Person> persons = new ArrayList<>();
        Person personChild = new Person();
        Person personAdult = new Person();

        //Child
        medicalRecordChild.setFirstName("Bobby");
        medicalRecordChild.setLastName("Dupont");
        medicalRecordChild.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecordChild);

        //Adult
        medicalRecordAdult.setFirstName("Sarah");
        medicalRecordAdult.setLastName("Dupont");
        medicalRecordAdult.setBirthdate("03/06/2000");
        medicalRecords.add(medicalRecordAdult);


        when(medicalRecordService.findAll())
                .thenReturn(medicalRecords);

        personChild.setFirstName("Bobby");
        personChild.setLastName("Dupont");
        personChild.setAddress("TestRoad");
        persons.add(personChild);

        personAdult.setFirstName("Sarah");
        personAdult.setLastName("Dupont");
        personAdult.setAddress("TestRoad");
        persons.add(personAdult);
        when(personService.findAll())
                .thenReturn(persons);

        ChildrenWithFamilyDTO result = childAlertService.getChildrenWithFamilyDTO("Invalid Address");

        assertThat(result.getChildren().isEmpty()).isTrue();
    }
}