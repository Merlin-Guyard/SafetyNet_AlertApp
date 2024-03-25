package com.mg.warning.firestation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.controller.FirestationController;
import com.mg.warning.model.Firestation;
import com.mg.warning.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FirestationController.class)
class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    private Firestation firestationTest1;
    private Firestation firestationTest2;
    private Firestation firestationTest3;

    @BeforeEach
    void setUp() throws Exception {
        firestationTest1 = new Firestation("AddressTest1", 1);
        firestationTest2 = new Firestation("AddressTest2", 2);
        firestationTest3 = new Firestation("AddressTest3", 2);
    }

    @Test
    void getAllFireStationsTest() throws Exception {

        when(firestationService.findAll()).thenReturn(List.of(
                firestationTest1, firestationTest2, firestationTest3));

        mockMvc.perform(get("/firestation/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].station", is(1)))
                .andExpect(jsonPath("$[1].station", is(2)))
                .andExpect(jsonPath("$[2].station", is(2)));
    }

    @Test
    void getAllFireStationsWhenNotFoundTest() throws Exception {

        when(firestationService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/firestation/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void saveFireStationsTest() throws Exception {

        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Firestation("TestAddress", 1)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutFirestation() throws Exception {

        mockMvc.perform(put("/firestation")
                        .content(asJsonString(new Firestation("TestAddress", 42)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        {
            mockMvc.perform(delete("/firestation/TestAddress/42")
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