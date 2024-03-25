package com.mg.warning.firestation;

import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(FirestationService.class)
public class FirestationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationRepository firestationRepository;

    private com.mg.warning.model.Firestation firestationTest1;
    private com.mg.warning.model.Firestation firestationTest2;
    private com.mg.warning.model.Firestation firestationTest3;

    @BeforeEach
    void setUp() throws Exception {
        firestationTest1 = new com.mg.warning.model.Firestation("AddressTest1", 1);
        firestationTest2 = new com.mg.warning.model.Firestation("AddressTest2", 2);
        firestationTest3 = new com.mg.warning.model.Firestation("AddressTest3", 2);
    }

    @Test
    void getAllFireStationsTest() throws Exception {

        when(firestationRepository.findAll()).thenReturn(List.of(
                firestationTest1, firestationTest2, firestationTest3));

        assertThat(firestationTest1.getStation()).isEqualTo(1);
        assertThat(firestationTest2.getStation()).isEqualTo(2);
        assertThat(firestationTest3.getStation()).isEqualTo(2);
    }
}
