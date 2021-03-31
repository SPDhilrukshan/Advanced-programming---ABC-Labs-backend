package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.controller.PatientController;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Patient;
import com.icbt.advancedprogramming.advancedprogramming.repository.PatientRepository;
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
public class PatientUnitTests {

    private static Patient patient;

//    @Mock
//    private PatientService PatientService;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientController patientController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        patient = new Patient();

        patient.setBloodGroup("O");
        patient.setOccupation("occupation test");
        patient.setAddress("address test");
        patient.setContactNumber("123456789");
        patient.setDateOfBirth(new Date());
        patient.setEmail("email test");
        patient.setFirstName("first name test");
        patient.setLastName("last name test");
        patient.setGender("male");
        patient.setMaritalStatus("single");
        patient.setNationality("sri lanka");
        patient.setNIC("1241245");
        patient.setPassword("abc");

    }

    @Test
    void findAll_whenNoRecord() {

//        Mockito.when(PatientService.getAllPatients()).thenReturn(Arrays.asList());
//        Assertions.assertEquals(PatientService.getAllPatients().size(), 0);
//        Mockito.verify(PatientService, Mockito.times(1)).getAllPatients();

        Mockito.when(patientRepository.findAll()).thenReturn(Arrays.asList());
        Assertions.assertEquals(patientRepository.findAll().size(), 0);
        Mockito.verify(patientRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        Patient patientSaved = patientRepository.save(patient);
        Mockito.verify(patientRepository,Mockito.times(1)).save(patient);

    }

    @Test
    void findById_WhenMatch() {

        try{
            ResponseEntity<Patient> PatientSaved = patientController.getPatientsById(6L);
            Assertions.assertEquals(patient.getFirstName(),PatientSaved.getBody().getFirstName());
        } catch (Exception e){

        }
    }

    @Test
    void deleteById_WhenFound() {

        try{
            patientController.deletePatientById(6L);
            Mockito.verify(patientRepository, Mockito.times(1)).deleteById(6L);
        } catch (Exception e){

        }

    }

}
