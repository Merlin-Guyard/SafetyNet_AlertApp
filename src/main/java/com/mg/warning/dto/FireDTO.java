package com.mg.warning.dto;

import java.util.List;

public class FireDTO {

    private List<FirePersonDTO> fireAlertPersonsDTO;
    private List<FireFireStationDTO> fireAlertStationDTO;

    public List<FireFireStationDTO> getFireAlertStationDTO() {
        return fireAlertStationDTO;
    }

    public void setFireAlertStationDTO(List<FireFireStationDTO> fireAlertStationDTO) {
        this.fireAlertStationDTO = fireAlertStationDTO;
    }

    public List<FirePersonDTO> getFireAlertPersonsDTO() {
        return fireAlertPersonsDTO;
    }

    public void setFireAlertPersonsDTO(List<FirePersonDTO> fireAlertPersonsDTO) {
        this.fireAlertPersonsDTO = fireAlertPersonsDTO;
    }

}
