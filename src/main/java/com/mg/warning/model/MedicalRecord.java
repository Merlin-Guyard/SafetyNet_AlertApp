package com.mg.warning.model;

import org.tinylog.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class MedicalRecord {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String[] medications;
    private String[] allergies;

    public MedicalRecord(String firstName, String lastName, String birthdate, String[] medications, String[] allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public MedicalRecord() {
    }

    public MedicalRecord(String[] medication, String[] allergies) {
        this.medications = medication;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medication=" + Arrays.toString(medications) +
                ", allergies=" + Arrays.toString(allergies) +
                '}';
    }

    public int getAgeFromMedicalRecords(List<MedicalRecord> medicalRecordList, String firstname, String lastname) {

        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
                Logger.debug("getting age for {}, {}", firstname, lastname);
                int result = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
                return result;
            }
        }
        Logger.error("Can't get age from medicalRecords");
        return -1;
    }
}
