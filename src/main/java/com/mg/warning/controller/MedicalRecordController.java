package com.mg.warning.controller;

import com.mg.warning.model.MedicalRecord;
import com.mg.warning.service.MedicalRecordService;
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
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = "/GetAll")
    public List<MedicalRecord> getAllMedicalRecords() {
        Logger.info("/medicalRecord/GetAll function get all called");
        return medicalRecordService.findAll();
    }

    @PostMapping(value = "")
    public void postOneMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Logger.info("/medicalRecord function post called for {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        medicalRecordService.save(medicalRecord);
    }

    @PutMapping(value = "")
    public void updateOneMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Logger.info("/medicalRecord function update called for {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        medicalRecordService.update(medicalRecord);
    }

    @DeleteMapping(value = "/{firstname}/{lastname}")
    public void delOneMedicalRecord(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        Logger.info("/medicalRecord function delete called for {}, {}", firstname, lastname);
        medicalRecordService.delete(firstname, lastname);
    }
}
