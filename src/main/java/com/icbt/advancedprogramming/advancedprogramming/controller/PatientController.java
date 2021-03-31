package com.icbt.advancedprogramming.advancedprogramming.controller;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Patient;
import com.icbt.advancedprogramming.advancedprogramming.repository.PatientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(basePath = "/patient", value = "Data Tier: ABC Employees Service APIs", description = "Data Tier: All services related ABC Lab  Employees", produces = "application/json")
@RestController
@RequestMapping("/patient")
@Service
public class PatientController {

    private static Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientRepository patientRepository;

    @ApiOperation(value = "create or update a patient record", notes = "This API is used to create or update a patient record")
    @PostMapping()
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {

        //validation goes here
        if(patient.getFirstName().isEmpty()){
            logger.error("Validation: Patient's first name cannot be empty");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(patient.getLastName().isEmpty()){
            logger.error("Validation: Patient's last name cannot be empty");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(patient.getBloodGroup().isEmpty()){
            logger.error("Validation: Patient's blood group is mandatory");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(patient.getDateOfBirth() == null){
            logger.error("Validation: Patient's date of birth is mandatory");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Patient patientSaved = new Patient();
        try{
            patientSaved = patientRepository.save(patient);
        }catch(Exception e){
            //e.printStackTrace();
            logger.error("Error while saving");
        }

        if (patientSaved == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(patientSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "GET a patient record", notes = "This API is used to GET a patient record by patient Id")
    @CrossOrigin
    @RequestMapping(path = "/patient-id/{patientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> getPatientsById(@PathVariable("patientId") Long patientId ) throws Exception {

        if(patientId == null){
            logger.error("Validation: patient ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Patient patient = patientRepository.findByPatientId(patientId);
        if(patient == null){
            return new ResponseEntity(new HttpHeaders(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(patient, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET all patient records", notes = "This API is used to GET all patient records")
    @CrossOrigin
    @RequestMapping(path = "/all-patients/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getAllPatients() throws Exception {

        List<Patient> patients = patientRepository.findAll();
        if(patients == null || patients.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(patients, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "DELETE a patient record", notes = "This API is used to DELETE a patient record by patient Id")
    @CrossOrigin
    @RequestMapping(path = "/delete/{patientId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePatientById(@PathVariable("patientId") Long patientId ) throws Exception {

        if(patientId == null){
            logger.error("Validation: patient ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        patientRepository.deleteById(patientId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
