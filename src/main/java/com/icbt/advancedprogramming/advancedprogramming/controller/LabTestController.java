package com.icbt.advancedprogramming.advancedprogramming.controller;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Appointment;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import com.icbt.advancedprogramming.advancedprogramming.repository.LabTestRepository;
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

@Api(basePath = "/lab-test", value = "Data Tier: ABC Lab Test Service APIs", description = "Data Tier: All services related ABC Lab Tests", produces = "application/json")
@RestController
@RequestMapping("/lab-test")
@Service
public class LabTestController {

    @Autowired
    LabTestRepository labTestRepository;

    private static Logger logger = LogManager.getLogger(LabTestController.class);

    @ApiOperation(value = "create or update an Lab test record", notes = "This API is used to create or update an Lab test record")
    @PostMapping()
    public ResponseEntity<LabTest> create(@RequestBody LabTest labTest) {

        //validation goes here
        if(labTest.getLabTestcost() == null){
            logger.error("Validation: Lab Test is mandatory");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(labTest.getLabTestName().isEmpty()){
            logger.error("Validation: Lab Test Name is mandatory");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        LabTest labTestSaved = new LabTest();

        try{
            labTestSaved = labTestRepository.save(labTest);
        }catch(Exception e){
            //e.printStackTrace();
            logger.error("Error while saving Lab test");
        }

        if (labTestSaved == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(labTestSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "GET all lab test records", notes = "This API is used to GET all lab test records")
    @CrossOrigin
    @RequestMapping(path = "/all-labtests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LabTest>> getAllLabTests() throws Exception {

        List<LabTest> labTestList = labTestRepository.findAll();
        if(labTestList == null || labTestList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(labTestList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET a lab test record by lab test id", notes = "This API is used to GET a lab test record by lab test id")
    @CrossOrigin
    @RequestMapping(path = "/lab-test/{labTestId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LabTest> getLabTestById(@PathVariable("labTestId") Long labTestId) throws Exception {

        if(labTestId == null){
            logger.error("Validation: lab test ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        LabTest labTest = labTestRepository.findByLabTestId(labTestId);
        if(labTest == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(labTest, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "delete a lab test record", notes = "This API is used to delete a lab test record using lab test Id")
    @CrossOrigin
    @RequestMapping(path = "/lab-test/remove/{labTestId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteLabTestById(@PathVariable("labTestId") Long labTestId) throws Exception {

        if(labTestId == null){
            logger.error("Validation: lab test id cannot be empty");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        labTestRepository.deleteById(labTestId);
        return new ResponseEntity( HttpStatus.ACCEPTED);
    }

}
