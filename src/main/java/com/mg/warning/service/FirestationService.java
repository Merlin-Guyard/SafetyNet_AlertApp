package com.mg.warning.service;

import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    public List<Firestation> findAll() {
        return firestationRepository.findAll();
    }

    public void save(Firestation firestation) {
        firestationRepository.save(firestation);
    }

    public void update(Firestation firestation) {
        firestationRepository.update(firestation);
    }

    public void delete(String address, int station) {
        firestationRepository.delete(address, station);
    }

    public List<Firestation> findByAddress(String address) {
        return firestationRepository.findByAddress(address);
    }

    public List<Firestation> findByStationNumber(int stationNumber) {
        return firestationRepository.findByStationNumber(stationNumber);
    }
}