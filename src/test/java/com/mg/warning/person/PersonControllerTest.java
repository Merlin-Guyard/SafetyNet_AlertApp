package com.mg.warning.person;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.controller.PersonController;
import com.mg.warning.model.Person;
import com.mg.warning.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService PersonService;

    private Person PersonTest1;
    private Person PersonTest2;
    private Person PersonTest3;

    @BeforeEach
    void setUp() throws Exception {
        PersonTest1 = new Person("TestFirstName1", "TestLastName1", "TestAddress1", "", 0,"","");
        PersonTest2 = new Person("TestFirstName2", "TestLastName3", "TestAddress2", "", 0,"","");
        PersonTest3 = new Person("TestFirstName2", "TestLastName3", "TestAddress2", "", 0,"","");
    }

    @Test
    void getAllMedicalrecordsTest() throws Exception {

        when(PersonService.findAll()).thenReturn(List.of(
                PersonTest1, PersonTest2, PersonTest3));

        mockMvc.perform(get("/person/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].address", is("TestAddress1")))
                .andExpect(jsonPath("$[1].address", is("TestAddress2")))
                .andExpect(jsonPath("$[2].address", is("TestAddress2")));
    }

    @Test
    void getAllFireStationsWhenNotFoundTest() throws Exception {

        when(PersonService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/person/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void saveFireStationsTest() throws Exception {

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Person("TestFirstName1", "TestLastName1", "TestAddress1", "", 0,"","")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutFirestation() throws Exception {

        mockMvc.perform(put("/person")
                        .content(asJsonString(new Person("TestFirstName1", "TestLastName1", "TestAddress1", "", 0,"","")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        {
            mockMvc.perform(delete("/person/TestFirstName/testLastName")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}