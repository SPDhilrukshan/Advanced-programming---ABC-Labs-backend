package com.icbt.advancedprogramming.advancedprogramming.controller;

import com.icbt.advancedprogramming.advancedprogramming.config.Constants;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Billing;
import com.icbt.advancedprogramming.advancedprogramming.repository.BillingRepositroy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(basePath = "/patient", value = "Data Tier: ABC Employees Service APIs", description = "Data Tier: All services related ABC Lab  Employees", produces = "application/json")
@RestController
@RequestMapping("/billing")
@Service
public class BillingController {

    @Autowired
    BillingRepositroy billingRepositroy;

    private static Logger logger = LogManager.getLogger(AppointmentController.class);

    @ApiOperation(value = "GET all billing records", notes = "This API is used to GET all billing records")
    @CrossOrigin
    @RequestMapping(path = "/all-billing/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBillings() throws Exception {

        List<Billing> billingList = billingRepositroy.findAll();
        if(billingList == null || billingList.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(billingList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "save and update the billing record", notes = "This API is used to save and update the billing record")
    @PostMapping()
    public ResponseEntity create(@RequestBody Billing billing) {

        if(billing.getPatientId() == null){
            logger.error("Validation: patient Id is required");
            return new ResponseEntity<>("Validation: patient Id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getAppointmentId() == null){
            logger.error("Validation: Appointment Id is required");
            return new ResponseEntity<>("Validation: Appointment Id is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getBilledAmount() == null){
            logger.error("Validation: Billed Amount is required");
            return new ResponseEntity<>("Validation: Billed Amount is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(billing.getBilledDate() == null){
            logger.error("Validation: Billed Date is required");
            return new ResponseEntity<>("Validation: Billed Date is required", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Billing billingSaved = billingRepositroy.save(billing);

        if(billingSaved == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(billingSaved, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET billing records between a date range", notes = "This API is used to GET billing records between a date range")
    @CrossOrigin
    @RequestMapping(path = "/date-range/start-date/{sDate}/end-date/{eDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBillingsByDateRange(@PathVariable("sDate") String sDate, @PathVariable("eDate") String eDate) throws Exception {

        if(sDate == null){
            logger.error("Validation: Start date is required");
            return new ResponseEntity<>("Validation: Start date is required", HttpStatus.BAD_REQUEST);
        }

        if(eDate == null){
            logger.error("Validation: End date is required");
            return new ResponseEntity<>("Validation: End date is required", HttpStatus.BAD_REQUEST);
        }

        Date startDate;
        Date endDate;

        startDate = Constants.sdf.parse(sDate);
        endDate = Constants.sdf.parse(eDate);

        List<Billing> appointmentList = billingRepositroy.findAllByBilledDateBetween(startDate,endDate);
        if(appointmentList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(appointmentList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "DELETE a billing record", notes = "This API is used to DELETE a billing record by billing Id")
    @CrossOrigin
    @RequestMapping(path = "/delete/{billingId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBillingById(@PathVariable("billingId") Long billingId ) throws Exception {

        if(billingId == null){
            logger.error("Validation: billing ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        billingRepositroy.deleteById(billingId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET a billing record", notes = "This API is used to GET a billing record by billing Id")
    @CrossOrigin
    @RequestMapping(path = "/delete/{billingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBillingById(@PathVariable("billingId") Long billingId ) throws Exception {

        if(billingId == null){
            logger.error("Validation: billing ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Billing billing = billingRepositroy.findByBillingId(billingId);

        if(billing == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
