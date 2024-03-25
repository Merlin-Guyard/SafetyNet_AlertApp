package com.mg.warning.alert.firestation;

import com.mg.warning.dto.FirestationWithNbDTO;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.model.Person;
import com.mg.warning.model.Firestation;
import com.mg.warning.service.FirestationAlertService;
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
public class FireStationServiceTest {

    @Mock
    private FirestationService firestationService = mock(FirestationService.class);

    @Mock
    private PersonService personService = mock(PersonService.class);

    @Mock
    private MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    @InjectMocks
    private FirestationAlertService service = new FirestationAlertService();

    @Test
    public void testFirestationChild() {

        MedicalRecord medicalRecordChild = new MedicalRecord();
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new com.mg.warning.model.Firestation();

        firestation.setAddress("TestRoad");
        firestation.setStation(3);
        firestations.add(firestation);
        when(firestationService.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        persons.add(person);
        when(personService.findByAddress(firestation.getAddress()))
                .thenReturn(persons);

        medicalRecordChild.setFirstName("Bobby");
        medicalRecordChild.setLastName("Dupont");
        medicalRecordChild.setBirthdate("03/06/2005");
        when(medicalRecordService.findByName(person.getFirstName(),person.getLastName()))
                .thenReturn(medicalRecordChild);

        FirestationWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(1);
    }
    @Test
    public void testFirestationAdult() {

        MedicalRecord medicalRecordChild = new MedicalRecord();
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new com.mg.warning.model.Firestation();

        firestation.setAddress("TestRoad");
        firestation.setStation(3);
        firestations.add(firestation);
        when(firestationService.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        persons.add(person);
        when(personService.findByAddress(firestation.getAddress()))
                .thenReturn(persons);

        medicalRecordChild.setFirstName("Bobby");
        medicalRecordChild.setLastName("Dupont");
        medicalRecordChild.setBirthdate("03/06/2000");
        when(medicalRecordService.findByName(person.getFirstName(),person.getLastName()))
                .thenReturn(medicalRecordChild);

        FirestationWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(1);
        assertThat(result.getNbChildrens()).isEqualTo(0);
    }

}