package com.mg.warning.repository;

import com.mg.warning.model.Firestation;
import org.springframework.stereotype.Repository;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    private List<Firestation> firestationList = new ArrayList<>();

    //GET
    public List<Firestation> findAll() {
        return firestationList;
    }

    //GET BY ID
    public List<Firestation> findByStationNumber(int stationNumber) {
        List<Firestation> result = new ArrayList<>();
        for (Firestation firestation : firestationList) {
            Logger.debug("vérification de la caserne n°{} à {}", firestation.getAddress(), firestation.getAddress());
            if (firestation.getStation() == stationNumber) {
                result.add(firestation);
                Logger.debug("firestation ajoutée");
            }
        }
        return result;
    }

    //GET BY ADDRESS
    public List<Firestation> findByAddress(String address) {
        List<Firestation> result = new ArrayList<>();
        for (Firestation firestation : firestationList) {
            Logger.debug("vérification de la caserne n°{} à {}", firestation.getAddress(), firestation.getAddress());
            if (firestation.getAddress().equals(address)) {
                result.add(firestation);
                Logger.debug("firestation ajoutée");
            }
        }
        return result;
    }

    //PUT
    public void update(Firestation firestation) {
        int index = 0;
        List<Firestation> copy = new ArrayList<>(firestationList);
        for (Firestation firestationLoop : copy) {
            Logger.debug("vérification de la caserne n°{} à {}", firestationLoop.getAddress(), firestationLoop.getAddress());
            if (firestationLoop.getAddress().equals(firestation.getAddress())) {
                firestationList.remove(firestationLoop);
                firestationList.add(firestation);
                Logger.debug("firestation mise à jour");
            }
        }
    }

    //POST
    public void save(Firestation firestation) {
        firestationList.add(firestation);
    }

    //DELETE
    public void delete(String address, int station) {
        Logger.debug("suppression de la caserne n°{} à {}", station, address);
        firestationList.removeIf(firestation -> firestation.getAddress().equals(address) && firestation.getStation() == station);
    }


}