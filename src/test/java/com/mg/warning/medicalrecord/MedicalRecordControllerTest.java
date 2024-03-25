package com.mg.warning.medicalrecord;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.controller.MedicalRecordController;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.service.MedicalRecordService;
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

@WebMvcTest(MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    private MedicalRecord medicalRecordTest1;
    private MedicalRecord medicalRecordTest2;
    private MedicalRecord medicalRecordTest3;

    @BeforeEach
    void setUp() throws Exception {
        medicalRecordTest1 = new MedicalRecord("TestFirstName1", "TestLastName1", "01/01/2000", new String[]{""}, new String[]{""});
        medicalRecordTest2 = new MedicalRecord("TestFirstName2", "TestLastName2", "01/01/2001", new String[]{""}, new String[]{""});
        medicalRecordTest3 = new MedicalRecord("TestFirstName3", "TestLastName3", "01/01/2002", new String[]{""}, new String[]{""});
    }

    @Test
    void getAllMedicalrecordsTest() throws Exception {

        when(medicalRecordService.findAll()).thenReturn(List.of(
                medicalRecordTest1, medicalRecordTest2, medicalRecordTest3));

        mockMvc.perform(get("/medicalRecord/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].birthdate", is("01/01/2000")))
                .andExpect(jsonPath("$[1].birthdate", is("01/01/2001")))
                .andExpect(jsonPath("$[2].birthdate", is("01/01/2002")));
    }

    @Test
    void getAllFireStationsWhenNotFoundTest() throws Exception {

        when(medicalRecordService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/medicalRecord/GetAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void saveFireStationsTest() throws Exception {

        mockMvc.perform(post("/medicalRecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new MedicalRecord("TestFirstName1", "TestLastName1", "01/01/2000", new String[]{""}, new String[]{""})))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutFirestation() throws Exception {

        mockMvc.perform(put("/medicalRecord")
                        .content(asJsonString(new MedicalRecord("TestFirstName1", "TestLastName1", "01/01/2000", new String[]{""}, new String[]{""})))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        {
            mockMvc.perform(delete("/medicalRecord/TestFirstName/testLastName")
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