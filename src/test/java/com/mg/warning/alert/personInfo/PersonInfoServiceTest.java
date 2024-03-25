package com.mg.warning.alert.personInfo;

import com.mg.warning.dto.PersonInfoDTO;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.FirestationService;
import com.mg.warning.service.MedicalRecordService;
import com.mg.warning.service.PersonInfoAlertService;
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
class PersonInfoServiceTest {

    @Mock
    private PersonService personService = mock(PersonService.class);

    @Mock
    private MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    @InjectMocks
    private PersonInfoAlertService service = new PersonInfoAlertService();

    @Test
    void testPersonInfo() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        persons.add(person);
        when(personService.findByFirstAndLastName(person.getFirstName(), person.getLastName()))
                .thenReturn(persons);

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2000");
        medicalRecords.add(medicalRecord);
        when(medicalRecordService.findByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(medicalRecords);

        List<PersonInfoDTO> resultList = service.getPersonInfoDTO(person.getFirstName(), person.getLastName());
        PersonInfoDTO result = resultList.get(0);

        assertThat(result.getLastName()).isEqualTo(person.getLastName());
        assertThat(result.getAge()).isEqualTo(22);
    }
}