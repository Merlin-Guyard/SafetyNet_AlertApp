package com.mg.warning.alert.phone;

import com.mg.warning.dto.PhoneDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.FirestationService;
import com.mg.warning.service.MedicalRecordService;
import com.mg.warning.service.PersonService;
import com.mg.warning.service.PhoneAlertService;
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
class PhoneServiceTest {

    @Mock
    private FirestationService firestationService = mock(FirestationService.class);

    @Mock
    private PersonService personService = mock(PersonService.class);

    @InjectMocks
    private PhoneAlertService service = new PhoneAlertService();

    @Test
    void testPhone() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationService.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);

        person.setAddress("TestRoad");
        person.setPhone("06 01 23 45 67");
        persons.add(person);
        when(personService.findByAddress(firestation.getAddress()))
                .thenReturn(persons);

        List<PhoneDTO> resultList = service.getPhoneDTO(firestation.getStation());
        PhoneDTO result = resultList.get(0);

        assertThat(result.getPhone()).isEqualTo(person.getPhone());
    }
}