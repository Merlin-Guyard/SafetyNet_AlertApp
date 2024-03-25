package com.mg.warning.alert.fire;

import com.mg.warning.dto.FireDTO;
import com.mg.warning.dto.FirePersonDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.FireAlertService;
import com.mg.warning.service.FirestationService;
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
class FireServiceTest {

    @Mock
    private PersonService personService = mock(PersonService.class);

    @Mock
    private MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    @Mock
    private FirestationService firestationService = mock((FirestationService.class));

    @InjectMocks
    private FireAlertService service = new FireAlertService();

    @Test
    void testFire() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        persons.add(person);
        when(personService.findByAddress(person.getAddress()))
                .thenReturn(persons);

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecord);
        when(medicalRecordService.findAll())
                .thenReturn(medicalRecords);


        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationService.findByAddress(firestation.getAddress()))
                .thenReturn(firestations);

        FireDTO result =  service.getFireDTO(firestation.getAddress());
        List<FirePersonDTO> resultPersonList = result.getFireAlertPersonsDTO();
        FirePersonDTO resultPerson = resultPersonList.get(0);

        assertThat(resultPerson.getLastName()).isEqualTo(person.getLastName());
    }

}