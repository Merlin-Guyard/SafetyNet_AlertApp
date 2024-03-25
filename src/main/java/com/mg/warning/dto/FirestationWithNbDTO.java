package com.mg.warning.dto;

import com.mg.warning.dto.FirestationDTO;

import java.util.ArrayList;
import java.util.List;

public class FirestationWithNbDTO {



    private List<FirestationDTO> firestationAlertDTOS = new ArrayList<>();
    private int nbAdults;
    private int nbChildrens;

    public List<FirestationDTO> getFirestationAlertDTOS() {
        return firestationAlertDTOS;
    }

    public void setFirestationAlertDTOS(List<FirestationDTO> firestationAlertDTOS) {
        this.firestationAlertDTOS = firestationAlertDTOS;
    }

    public int getNbAdults() {
        return nbAdults;
    }

    public void setNbAdults(int nbAdults) {
        this.nbAdults = nbAdults;
    }

    public int getNbChildrens() {
        return nbChildrens;
    }

    public void setNbChildrens(int nbChildrens) {
        this.nbChildrens = nbChildrens;
    }
}
