package com.mg.warning.configuration;

import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.List;

@Component
public class CheckHealthIndicator implements HealthIndicator {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Override
    public Health health() {
        List<Person> persons = personRepository.findAll();
        List<Firestation> fireStations = firestationRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        Health.Builder status = Health.up();
        Logger.debug("Vérification to status do token health");
        if (persons.isEmpty() || fireStations.isEmpty() || medicalRecords.isEmpty()) {
            Logger.debug("Status down");
            status = Health.down();
        }
        return status.build();
    }
}