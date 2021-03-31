package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.controller.BillingController;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Billing;
import com.icbt.advancedprogramming.advancedprogramming.repository.BillingRepositroy;
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
public class BillingUnitTests {

    private static Billing billing;

    @Mock
    private BillingRepositroy billingRepository;

    @InjectMocks
    private BillingController billingController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        billing = new Billing();

        billing.setAppointmentId(1111L);
        billing.setBilledAmount(1234L);
        billing.setBilledDate(new Date());
        billing.setBillingType("type test");
        billing.setPatientId(123L);


    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(billingRepository.findAll()).thenReturn(Arrays.asList());
        Assertions.assertEquals(billingRepository.findAll().size(), 0);
        Mockito.verify(billingRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        Billing billingSaved = billingRepository.save(billing);
        Mockito.verify(billingRepository,Mockito.times(1)).save(billing);

    }

    @Test
    void findById_WhenMatch() {

        try{
            ResponseEntity<Billing> PatientSaved = billingController.getBillingById(6L);
            Assertions.assertEquals(billing.getPatientId(),PatientSaved.getBody().getPatientId());
        } catch (Exception e){

        }
    }

    @Test
    void deleteById_WhenFound() {

        try{
            billingController.deleteBillingById(6L);
            Mockito.verify(billingRepository, Mockito.times(1)).deleteById(6L);
        } catch (Exception e){

        }

    }
}
