package com.mg.warning.alert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.controller.AlertController;
import com.mg.warning.dto.*;
import com.mg.warning.model.Person;
import com.mg.warning.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlertController.class)
class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationAlertService firestationAlertService;

    @MockBean
    private ChildAlertService childAlertService;

    @MockBean
    private PhoneAlertService phoneAlertService;

    @MockBean
    private FireAlertService fireAlertService;

    @MockBean
    private FloodAlertService floodAlertService;

    @MockBean
    private PersonInfoAlertService personInfoAlertService;

    @MockBean
    private EmailAlertService emailAlertService;

    @Test
    void getFirestationTest() throws Exception {

        List<FirestationDTO> firestationDTOS = new ArrayList<>();
        FirestationDTO firestationDTO = new FirestationDTO();
        firestationDTO.setFirstname("Bobby");
        firestationDTO.setLastname("Dupont");
        firestationDTO.setAddress("Strawberry road");
        firestationDTO.setPhone("06 06 06 06 06");
        firestationDTOS.add(firestationDTO);

        FirestationWithNbDTO firestationWithNbDTO = new FirestationWithNbDTO();
        firestationWithNbDTO.setFirestationAlertDTOS(firestationDTOS);
        firestationWithNbDTO.setNbAdults(1);
        firestationWithNbDTO.setNbChildrens(2);

        when(firestationAlertService.getFirestationAlertDTOWithSum(42)).thenReturn(firestationWithNbDTO);

        mockMvc.perform(get("/firestation?stationNumber=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firestationAlertDTOS.[0].firstname", is("Bobby")))
                .andExpect(jsonPath("$.nbAdults", is(1)))
                .andExpect(jsonPath("$.nbChildrens", is(2)));
    }

    @Test
    void getFirestationEmptyTest() throws Exception {

        FirestationWithNbDTO firestationWithNbDTO = new FirestationWithNbDTO();

        when(firestationAlertService.getFirestationAlertDTOWithSum(42)).thenReturn(firestationWithNbDTO);

        mockMvc.perform(get("/firestation?stationNumber=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firestationAlertDTOS.[0].firstname").doesNotExist());
    }

    @Test
    void getChildTest() throws Exception {

        List<ChildrenDTO> childrenDTOS = new ArrayList<>();
        ChildrenDTO childrenDTO = new ChildrenDTO();
        childrenDTO.setFirstname("Bobby");
        childrenDTO.setLastname("Dupont");
        childrenDTO.setAge(16);
        childrenDTOS.add(childrenDTO);

        List<FamilyDTO> familyDTOS = new ArrayList<>();
        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setFirstname("Sarah");
        familyDTO.setLastname("Dupont");
        familyDTOS.add(familyDTO);

        ChildrenWithFamilyDTO childrenWithFamilyDTO = new ChildrenWithFamilyDTO();
        childrenWithFamilyDTO.setChildren(childrenDTOS);
        childrenWithFamilyDTO.setFamily(familyDTOS);

        when(childAlertService.getChildrenWithFamilyDTO("TestAddress")).thenReturn(childrenWithFamilyDTO);

        mockMvc.perform(get("/childAlert?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.children.[0].firstname", is("Bobby")))
                .andExpect(jsonPath("$.family.[0]firstname", is("Sarah")));
    }

    @Test
    void getChildEmptyTest() throws Exception {

        ChildrenWithFamilyDTO childrenWithFamilyDTO = new ChildrenWithFamilyDTO();

        when(childAlertService.getChildrenWithFamilyDTO("TestAddress")).thenReturn(childrenWithFamilyDTO);

        mockMvc.perform(get("/childAlert?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.children.[0].firstname").doesNotExist());
    }

    @Test
    void getPhoneTest() throws Exception {

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setPhone("06 06 06 06 06");
        phoneDTOS.add(phoneDTO);

        when(phoneAlertService.getPhoneDTO(42)).thenReturn(phoneDTOS);

        mockMvc.perform(get("/phoneAlert?firestation=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].phone", is("06 06 06 06 06")));
    }

    @Test
    void getPhoneEmptyTest() throws Exception {

        List<PhoneDTO> phoneDTOS = new ArrayList<>();

        when(phoneAlertService.getPhoneDTO(42)).thenReturn(phoneDTOS);

        mockMvc.perform(get("/phoneAlert?firestation=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].phone").doesNotExist());
    }


    @Test
    void getFireTest() throws Exception {

        List<FirePersonDTO> firePersonDTOS = new ArrayList<>();
        FirePersonDTO firePersonDTO = new FirePersonDTO();
        firePersonDTO.setLastName("Dupont");
        firePersonDTO.setAge(16);
        firePersonDTOS.add(firePersonDTO);

        List<FireFireStationDTO> fireFireStationDTOS = new ArrayList<>();
        FireFireStationDTO fireFireStationDTO = new FireFireStationDTO();
        fireFireStationDTO.setStation(42);
        fireFireStationDTOS.add(fireFireStationDTO);

        FireDTO fireDTO = new FireDTO();
        fireDTO.setFireAlertPersonsDTO(firePersonDTOS);
        fireDTO.setFireAlertStationDTO(fireFireStationDTOS);

        when(fireAlertService.getFireDTO("TestAddress")).thenReturn(fireDTO);

        mockMvc.perform(get("/fire?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fireAlertPersonsDTO.[0].lastName", is("Dupont")))
                .andExpect(jsonPath("$.fireAlertStationDTO.[0].station", is(42)));
    }

    @Test
    void getFireEmptyTest() throws Exception {

        List<FirePersonDTO> firePersonDTOS = new ArrayList<>();
        List<FireFireStationDTO> fireFireStationDTOS = new ArrayList<>();

        FireDTO fireDTO = new FireDTO();
        fireDTO.setFireAlertPersonsDTO(firePersonDTOS);
        fireDTO.setFireAlertStationDTO(fireFireStationDTOS);

        when(fireAlertService.getFireDTO("TestAddress")).thenReturn(fireDTO);

        mockMvc.perform(get("/fire?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fireAlertPersonsDTO.[0].lastName").doesNotExist());
    }


    @Test
    void getFloodTest() throws Exception {

        List<FloodPersonsDTO> floodPersonsDTOS = new ArrayList<>();
        FloodPersonsDTO floodPersonsDTO = new FloodPersonsDTO();
        floodPersonsDTO.setLastName("Dupont");
        floodPersonsDTO.setAge(16);
        floodPersonsDTOS.add(floodPersonsDTO);

        List<FloodDTO> floodDTOS = new ArrayList<>();
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress("TestAddress");
        floodDTO.setFloodAlertPersonsDTO(floodPersonsDTOS);
        floodDTOS.add(floodDTO);

        when(floodAlertService.getFloodDTO(new int[]{42})).thenReturn(floodDTOS);

        mockMvc.perform(get("/flood/stations?stations=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].address", is("TestAddress")))
                .andExpect(jsonPath("$.[0].floodAlertPersonsDTO.[0].lastName", is("Dupont")));
    }
    @Test
    void getFloodEmptyTest() throws Exception {

        List<FloodDTO> floodDTOS = new ArrayList<>();

        when(floodAlertService.getFloodDTO(new int[]{42})).thenReturn(floodDTOS);

        mockMvc.perform(get("/flood/stations?stations=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].address").doesNotExist());
    }

    @Test
    void getPersonInfoTest() throws Exception {

        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setLastName("Dupont");
        personInfoDTO.setAge(16);
        personInfoDTOS.add(personInfoDTO);

        when(personInfoAlertService.getPersonInfoDTO("Bobby", "Dupont")).thenReturn(personInfoDTOS);

        mockMvc.perform(get("/personInfo?firstName=Bobby&lastName=Dupont"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lastName", is("Dupont")))
                .andExpect(jsonPath("$.[0].age", is(16)));
    }

    @Test
    void getPersonInfoEmptyTest() throws Exception {

        List<PersonInfoDTO> personInfoDTOS = new ArrayList<>();

        when(personInfoAlertService.getPersonInfoDTO("Bobby", "Dupont")).thenReturn(personInfoDTOS);

        mockMvc.perform(get("/personInfo?firstName=Bobby&lastName=Dupont"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].lastName").doesNotExist());
    }

    @Test
    void getEmailTest() throws Exception {

        List<EmailDTO> emailDTOS = new ArrayList<>();
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail("Dupont@gmail.com");
        emailDTOS.add(emailDTO);

        when(emailAlertService.getEmailDTO("Paris")).thenReturn(emailDTOS);

        mockMvc.perform(get("/communityEmail?city=Paris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].email", is("Dupont@gmail.com")));
    }

    @Test
    void getEmailEmptyTest() throws Exception {

        List<EmailDTO> emailDTOS = new ArrayList<>();

        when(emailAlertService.getEmailDTO("Paris")).thenReturn(emailDTOS);

        mockMvc.perform(get("/communityEmail?city=Paris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].email").doesNotExist());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}