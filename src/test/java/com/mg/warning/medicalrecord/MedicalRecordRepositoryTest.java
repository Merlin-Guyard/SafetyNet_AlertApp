package com.mg.warning.medicalrecord;


import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MedicalRecordRepositoryTest {

    @InjectMocks
    private MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    @Test
    void testFindAll() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecords.get(0);
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToAdd.getBirthdate());
    }

    @Test
    void testUpdate() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        MedicalRecord medicalRecordToModify = new MedicalRecord();
        medicalRecordToModify.setFirstName("Bobby");
        medicalRecordToModify.setLastName("Dupont");
        medicalRecordToModify.setBirthdate("01/01/1995");
        medicalRecordRepository.update(medicalRecordToModify);

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecords.get(0);
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToModify.getBirthdate());
    }

    @Test
    void testUpdateNoMatch() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        MedicalRecord medicalRecordToModify = new MedicalRecord();
        medicalRecordToModify.setFirstName("Sarah");
        medicalRecordToModify.setLastName("Dupont");
        medicalRecordToModify.setBirthdate("01/01/1995");
        medicalRecordRepository.update(medicalRecordToModify);

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecords.get(0);
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToAdd.getBirthdate());
    }

    @Test
    void testDelete() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);
        medicalRecordRepository.delete("Bobby", "Dupont");

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        assertThat(medicalRecords.isEmpty()).isTrue();
    }

    @Test
    void testDeleteNoMatch() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);
        medicalRecordRepository.delete("Sarah", "Dupont");

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecords.get(0);
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToAdd.getBirthdate());
    }

    @Test
    void testFindByFirstAndLastNameList() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findByFirstAndLastName("Bobby", "Dupont"));
        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecords.get(0);
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToAdd.getBirthdate());
    }

    @Test
    void testFindByFirstAndLastNameListNoMatch() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findByFirstAndLastName("Sarah", "Dupont"));
        assertThat(medicalRecords.isEmpty()).isTrue();
    }

    @Test
    void testFindByFirstAndLastName() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecordRepository.findByName("Bobby", "Dupont");
        assertThat(medicalRecordToCheck.getBirthdate()).isEqualTo(medicalRecordToAdd.getBirthdate());
    }

    @Test
    void testFindByFirstAndLastNameNoMatch() {

        MedicalRecord medicalRecordToAdd = new MedicalRecord();
        medicalRecordToAdd.setFirstName("Bobby");
        medicalRecordToAdd.setLastName("Dupont");
        medicalRecordToAdd.setBirthdate("01/01/2000");
        medicalRecordRepository.save(medicalRecordToAdd);

        MedicalRecord medicalRecordToCheck = new MedicalRecord();
        medicalRecordToCheck = medicalRecordRepository.findByName("Sarah", "Dupont");
        assertThat(medicalRecordToCheck.getBirthdate()).isNull();
    }
}
