package com.mg.warning.service;

import com.mg.warning.dto.EmailDTO;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmailAlertService {

    @Autowired
    private PersonService personService;

    public List<EmailDTO> getEmailDTO(String city) {

        //Get and write data
        List<EmailDTO> dtoEmailList = getEmailDTOS(city);

        Logger.info("getEmailDTO executed successfully");
        return dtoEmailList;
    }

    private List<EmailDTO> getEmailDTOS(String city) {
        Logger.debug("getting and writing emails");
        List<Person> persons = new ArrayList<>(personService.findByCity(city));
        List<EmailDTO> dtoEmailList = new ArrayList<>();
        for (Person person : persons) {
            EmailDTO dtoEmail = new EmailDTO();
            dtoEmail.setEmail(person.getEmail());
            dtoEmailList.add(dtoEmail);
        }
        return dtoEmailList;
    }
}

