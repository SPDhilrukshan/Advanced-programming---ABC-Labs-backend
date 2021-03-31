package com.icbt.advancedprogramming.advancedprogramming.controller;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Employee;
import com.icbt.advancedprogramming.advancedprogramming.repository.EmployeeRepository;
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

@Api(basePath = "/employee", value = "Data Tier: ABC Employees Service APIs", description = "Data Tier: All services related ABC Lab  Employees", produces = "application/json")
@RestController
@RequestMapping("/employee")
@Service
public class EmployeeController {

    private static Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @ApiOperation(value = "create or update an employee record", notes = "This API is used to create or update an employee record")
    @PostMapping()
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {

        //validation goes here
        if(employee.getFirstName().isEmpty()){
            logger.error("Validation: Employee's first name cannot be empty");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(employee.getLastName().isEmpty()){
            logger.error("Validation: Employee's last name cannot be empty");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if(employee.getStaffType().isEmpty()){
            logger.error("Validation: Employee should have a staff type");
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Employee employeeSaved = new Employee();
        try{
            employeeSaved = employeeRepository.save(employee);
        }catch(Exception e){
            //e.printStackTrace();
            logger.error("Error while saving Employee");
        }

        if (employeeSaved == null) {
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeSaved, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "GET an employee record", notes = "This API is used to GET an employee record by employee Id")
    @CrossOrigin
    @RequestMapping(path = "/employee-id/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("employeeId") Long employeeId ) throws Exception {

        if(employeeId == null){
            return new ResponseEntity<>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepository.findByUserId(employeeId);
        if(employee == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employee, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "GET all employee records", notes = "This API is used to GET all employee records")
    @CrossOrigin
    @RequestMapping(path = "/all-employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {

        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employeeList, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Data Tier: GET an employee record by username", notes = "Data Tier: This API is used to GET an employee record by username")
    @CrossOrigin
    @RequestMapping(path = "/user/username/{username}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getUserByUsername(@PathVariable("username") String username) throws Exception {

        if(username.isEmpty()){
            logger.error("Validation: Username is empty");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        Employee employee = employeeRepository.findByUserName(username);

        if(employee == null || employee.getUserName().isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(employee, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "DELETE an employee record", notes = "This API is used to DELETE an employee record by employee Id")
    @CrossOrigin
    @RequestMapping(path = "/delete/{employeeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePatientById(@PathVariable("employeeId") Long employeeId ) throws Exception {

        if(employeeId == null){
            logger.error("Validation: employee ID is required");
            return new ResponseEntity<>( new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        employeeRepository.deleteById(employeeId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
