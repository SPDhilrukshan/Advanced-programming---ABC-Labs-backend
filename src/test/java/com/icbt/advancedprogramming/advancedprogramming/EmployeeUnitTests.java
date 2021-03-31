package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.controller.EmployeeController;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Employee;
import com.icbt.advancedprogramming.advancedprogramming.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath:test-context.xml")
public class EmployeeUnitTests {

    private static Employee employee;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        employee = new Employee();

        employee.setEmployeeAcceptStatus("status");
        employee.setStaffType("staff type test");
        employee.setUserName("username");
        employee.setAddress("address test");
        employee.setContactNumber("123456789");
        employee.setDateOfBirth(new Date());
        employee.setEmail("email test");
        employee.setFirstName("first name test");
        employee.setLastName("last name test");
        employee.setGender("male");
        employee.setMaritalStatus("single");
        employee.setNationality("sri lanka");
        employee.setNIC("1241245");
        employee.setPassword("abc");

    }

    @Test
    void findAll_whenNoRecord() {

//        Mockito.when(PatientService.getAllPatients()).thenReturn(Arrays.asList());
//        Assertions.assertEquals(PatientService.getAllPatients().size(), 0);
//        Mockito.verify(PatientService, Mockito.times(1)).getAllPatients();

        Mockito.when(employeeRepository.findAll()).thenReturn(Arrays.asList());
        Assertions.assertEquals(employeeRepository.findAll().size(), 0);
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        Employee patientSaved = employeeRepository.save(employee);
        Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);

    }

    @Test
    void findById_WhenMatch() {

        try{
            ResponseEntity<Employee> PatientSaved = employeeController.getEmployeeById(6L);
            Assertions.assertEquals(employee.getFirstName(),PatientSaved.getBody().getFirstName());
        } catch (Exception e){

        }
    }

    @Test
    void deleteById_WhenFound() {

        try{
            employeeController.deletePatientById(6L);
            Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(6L);
        } catch (Exception e){

        }

    }
}
