package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.controller.AppointmentController;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Appointment;
import com.icbt.advancedprogramming.advancedprogramming.model.enums.AppointmentStatus;
import com.icbt.advancedprogramming.advancedprogramming.repository.AppointmentRepository;
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
public class AppointmentUnitTests {

    private static Appointment appointment;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentController appointmentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        appointment = new Appointment();

        appointment.setAppointmentStatus(AppointmentStatus.REQUESTED.getAppointmentStatus());
        appointment.setAppointmentDate(new Date());
        appointment.setLabTestId(23L);
        appointment.setPatientId(123L);


    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(appointmentRepository.findAll()).thenReturn(Arrays.asList());
        Assertions.assertEquals(appointmentRepository.findAll().size(), 0);
        Mockito.verify(appointmentRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        Appointment billingSaved = appointmentRepository.save(appointment);
        Mockito.verify(appointmentRepository,Mockito.times(1)).save(appointment);

    }

    @Test
    void findById_WhenMatch() {

        try{
            ResponseEntity<Appointment> PatientSaved = appointmentController.getAppointmentById(6L);
            Assertions.assertEquals(appointment.getPatientId(),PatientSaved.getBody().getPatientId());
        } catch (Exception e){

        }
    }

    @Test
    void deleteById_WhenFound() {

        try{
            appointmentController.deleteAppointmentById(6L);
            Mockito.verify(appointmentRepository, Mockito.times(1)).deleteById(6L);
        } catch (Exception e){

        }

    }
}
