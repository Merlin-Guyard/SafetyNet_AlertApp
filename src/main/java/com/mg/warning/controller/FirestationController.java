package com.mg.warning.controller;

import com.mg.warning.model.Firestation;
import com.mg.warning.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @GetMapping(value = "/GetAll")
    public List<Firestation> getAllFirestation() {
        Logger.info("/person function get all called");
        return firestationService.findAll();
    }

    @PostMapping(value = "")
    public void postOneFirestation(@RequestBody Firestation firestation) {
        Logger.info("/person function post called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationService.save(firestation);
    }

    @PutMapping(value = "")
    public void updateOnePerson(@RequestBody Firestation firestation) {
        Logger.info("/person function update called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationService.update(firestation);
    }

    @DeleteMapping(value = "/{address}/{station}")
    public void delOnePerson(@PathVariable("address") String address, @PathVariable("station") int station) {
        Logger.info("/person function update called for firestation number : {}", station);
        firestationService.delete(address, station);
    }

}
