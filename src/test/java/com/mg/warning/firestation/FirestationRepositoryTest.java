package com.mg.warning.firestation;

import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FirestationRepositoryTest {

    @InjectMocks
    private FirestationRepository firestationRepository = new FirestationRepository();

    @Test
    void testFindAll() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getAddress()).isEqualTo(firestationToAdd.getAddress());
    }

    @Test
    void testFindByStationNumber() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByStationNumber(42));
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getAddress()).isEqualTo(firestationToAdd.getAddress());
    }

    @Test
    void testFindByStationNumberNoMatch() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByStationNumber(999));
        assertThat(firestations.isEmpty()).isTrue();
    }

    @Test
    void testFindByAddress() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByAddress("TestAddress"));
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getStation()).isEqualTo(firestationToAdd.getStation());
    }

    @Test
    void testFindByAddressNoMatch() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByAddress("Bad address"));
        assertThat(firestations.isEmpty()).isTrue();
    }

    @Test
    void testSave() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getStation()).isEqualTo(firestationToAdd.getStation());
    }

    @Test
    void testUpdate() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        Firestation firestationToUpdate = new Firestation();
        firestationToUpdate.setAddress("TestAddress");
        firestationToUpdate.setStation(999);

        firestationRepository.save(firestationToAdd);
        firestationRepository.update(firestationToUpdate);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        Firestation firestationToCheck = new Firestation();
        firestationToCheck = firestations.get(0);
        assertThat(firestationToCheck.getStation()).isEqualTo(firestationToUpdate.getStation());
    }

    @Test
    void testUpdateNoMatch() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        Firestation firestationToUpdate = new Firestation();
        firestationToUpdate.setAddress("TestAddress2");
        firestationToUpdate.setStation(999);

        firestationRepository.save(firestationToAdd);
        firestationRepository.update(firestationToUpdate);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        Firestation firestationToCheck = new Firestation();
        firestationToCheck = firestations.get(0);
        assertThat(firestationToCheck.getStation()).isEqualTo(firestationToAdd.getStation());
    }

    @Test
    void testDelete() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);
        firestationRepository.delete("TestAddress", 42);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        assertThat(firestations.isEmpty()).isTrue();
    }

    @Test
    void testDeleteNoMatch() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);
        firestationRepository.delete("TestAddress2", 42);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findAll());
        Firestation firestationToCheck = new Firestation();
        firestationToCheck = firestations.get(0);
        assertThat(firestationToCheck.getStation()).isEqualTo(firestationToAdd.getStation());
    }

}
