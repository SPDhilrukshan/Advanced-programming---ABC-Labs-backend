package com.icbt.advancedprogramming.advancedprogramming.controller;

import com.icbt.advancedprogramming.advancedprogramming.config.Constants;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Appointment;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.AppointmentRequestBodyDAO;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Patient;
import com.icbt.advancedprogramming.advancedprogramming.repository.AppointmentRepository;
import com.icbt.advancedprogramming.advancedprogramming.repository.LabTestRepository;
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

import java.util.Date;
import java.util.List;

@Api(basePath = "/appointment", value = "Data Tier: ABC Appointments APIs", description = "Data Tier: All services related ABC Lab Appointments", produces = "application/json")
@RestController
@RequestMapping("/appointment")
@Service
public class AppointmentController {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    LabTestRepository labTestRepository;

    private static Logger logger = LogManager.getLogger(AppointmentController.class);

    @ApiOperation(value = "create or update an appointment record", notes = "This API is used to create or update an appointment record")
    @PostMapping()
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {

        if(appointment.getAppointmentDate() == null){
            logger.error("Validation: Appointment date is mandatory");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(appointment.getPatientId() == null){
            logger.error("Validation: patient is needed for appointment");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(appointment.getLabTestId() == null){
            logger.error("Validation: lab test is needed for appointment");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Appointment appointmentSaved = new Appointment();

        try{
            Patient patientToSave = patientRepository.findByPatientId(appointment.getPatientId());

            LabTest labTestToSave = labTestRepository.findByLabTestId(appointment.getLabTestId());

            if(patientToSave == null){
                logger.error("Validation: No such Patient exist");
                return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }

            if(labTestToSave == null){
                logger.error("Validation: No such Lab Test exist");
                return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }

            appointmentSaved = appointmentRepository.save(appointment);

        }catch(Exception e){
            //e.printStackTrace();
            logger.error("Error while saving Appointment");
        }

        if (appointmentSaved == null) {
            logger.error("Error while saving");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(appointmentSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "GET an appointment record by appointment Id", notes = "This API is used to GET an appointment record by appointment Id")
    @CrossOrigin
    @RequestMapping(path = "/appointment-id/{appointmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("appointmentId") Long appointmentId ) throws Exception {

        if(appointmentId == null){
            logger.error("Validation: Appointment Id is null");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
        if(appointment == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointment, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET all appointment records", notes = "This API is used to create all appointment records")
    @CrossOrigin
    @RequestMapping(path = "/all-appointments/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> getAllAppointments() throws Exception {


        List<Appointment> appointmentList = appointmentRepository.findAll();
        if(appointmentList == null || appointmentList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET appointment records by patient Id", notes = "This API is used to GET appointment records by patient Id")
    @CrossOrigin
    @RequestMapping(path = "/appointment-id/patient-id/{patientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable("patientId") Long patientId) throws Exception {

        if(patientId == null){
            logger.error("Validation: Patient ID is null");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        List<Appointment> appointmentList = appointmentRepository.findAllByPatientId(patientId);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET appointment records between a date range", notes = "This API is used to GET appointment records between a date range")
    @CrossOrigin
    @RequestMapping(path = "/date-range/start-date/{sDate}/end-date/{eDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Appointment>> getAppointmentsByDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate) throws Exception {

        if(sDate == null){
            logger.error("Validation: Start date is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(eDate == null){
            logger.error("Validation: End date is required");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Date startDate;
        Date endDate;

        startDate = Constants.sdf.parse(sDate);
        endDate = Constants.sdf.parse(eDate);

        List<Appointment> appointmentList = appointmentRepository.findAllByAppointmentDateBetween(startDate,endDate);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "DELETE an appointment record", notes = "This API is used to DELETE an appointment record by appointment Id")
    @CrossOrigin
    @RequestMapping(path = "/delete/{appointmentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAppointmentById(@PathVariable("appointmentId") Long appointmentId ) throws Exception {

        if(appointmentId == null){
            logger.error("Validation: Appointment ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        appointmentRepository.deleteById(appointmentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
