package com.mg.warning.repository;

import com.mg.warning.model.MedicalRecord;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    //GET
    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    //GET BY FIRSTNAME & LASTNAME
    public List<MedicalRecord> findByFirstAndLastName(String firstname, String lastname) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            Logger.debug("vérification de {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
                result.add(medicalRecord);
                Logger.debug("medicalrecord ajouté");
            }
        }
        return result;
    }

    //PUT
    public void update(MedicalRecord medicalRecord) {
        int index = 0;
        for (MedicalRecord medicalRecordLoop : medicalRecordList) {
            Logger.debug("vérification de {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
            if (medicalRecordLoop.getFirstName().equals(medicalRecord.getFirstName()) && medicalRecordLoop.getLastName().equals(medicalRecord.getLastName())) {
                medicalRecordList.set(index, medicalRecord);
                Logger.debug("medicalrecord mis à jour");
                index++;
            }
        }
    }

    //POST
    public void save(MedicalRecord medicalRecord) {
        Logger.debug("ajout des medicalrecords");
        medicalRecordList.add(medicalRecord);
    }

    //DELETE
    public void delete(String firstname, String lastname) {
        Logger.debug("suppression de {}, {}", firstname, lastname);
        medicalRecordList.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname));
    }

    //GET BY NAME
    public MedicalRecord findByName(String firstname, String lastname) {
        MedicalRecord result = new MedicalRecord();
        for (MedicalRecord medicalRecord : medicalRecordList) {
            Logger.debug("vérification de {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
                result = medicalRecord;
                Logger.debug("medicalrecord ajouté");
            }
        }
        return result;
    }
}
