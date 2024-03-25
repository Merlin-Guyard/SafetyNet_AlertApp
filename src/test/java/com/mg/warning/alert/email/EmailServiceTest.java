package com.mg.warning.alert.email;

import com.mg.warning.dto.EmailDTO;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.EmailAlertService;
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
class EmailServiceTest {

    @Mock
    private PersonService personService = mock(PersonService.class);

    @InjectMocks
    private EmailAlertService service = new EmailAlertService();

    @Test
    void testEmail() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();

        person.setCity("TestCity");
        person.setEmail("Test@email.com");
        persons.add(person);
        when(personService.findByCity(person.getCity()))
                .thenReturn(persons);

        List<EmailDTO> resultList =  service.getEmailDTO(person.getCity());
        EmailDTO result = resultList.get(0);

        assertThat(result.getEmail()).isEqualTo(person.getEmail());
    }
}