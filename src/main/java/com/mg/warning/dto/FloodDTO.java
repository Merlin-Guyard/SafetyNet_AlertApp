package com.mg.warning.dto;

import java.util.List;

public class FloodDTO {

    String address;
    List<FloodPersonsDTO> floodAlertPersonsDTO;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonsDTO> getFloodAlertPersonsDTO() {
        return floodAlertPersonsDTO;
    }

    public void setFloodAlertPersonsDTO(List<FloodPersonsDTO> floodAlertPersonsDTO) {
        this.floodAlertPersonsDTO = floodAlertPersonsDTO;
    }
}
