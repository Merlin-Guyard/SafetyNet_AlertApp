package com.mg.warning.alert.flood;

import com.mg.warning.dto.FloodDTO;
import com.mg.warning.dto.FloodPersonsDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.FirestationService;
import com.mg.warning.service.FloodAlertService;
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
class FloodServiceTest {

    @Mock
    private FirestationService firestationService = mock(FirestationService.class);

    @Mock
    private PersonService personService = mock(PersonService.class);

    @Mock
    private MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    @InjectMocks
    private FloodAlertService service = new FloodAlertService();

    @Test
    void testFlood() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecord);
        when(medicalRecordService.findAll())
                .thenReturn(medicalRecords);

        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationService.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);
        int[] stationNumber = new int[1];
        stationNumber[0] = firestation.getStation();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        person.setPhone("06 01 23 45 67");
        persons.add(person);
        when(personService.findByAddress(firestation.getAddress()))
                .thenReturn(persons);

        List<FloodDTO> resultList =  service.getFloodDTO(stationNumber);
        FloodDTO result = resultList.get(0);
        FloodPersonsDTO floodPersonsDTO = result.getFloodAlertPersonsDTO().get(0);


        assertThat(floodPersonsDTO.getAge()).isEqualTo(17);
    }
}