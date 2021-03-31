package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.Service.LabTestService;
import com.icbt.advancedprogramming.advancedprogramming.controller.LabTestController;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import com.icbt.advancedprogramming.advancedprogramming.repository.LabTestRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath:test-context.xml")
public class LabTestsUnitTest {

    private static LabTest labTest1;
    private static LabTest labTest2;
    private static LabTest labTest3;

    @Mock
    private LabTestService labTestService;

    @Mock
    private LabTestRepository labTestRepository;

    @InjectMocks
    private LabTestController labTestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        labTest1 = new LabTest();
        labTest1.setLabTestName("abc");
        labTest1.setLabTestdescription("def");
        labTest1.setLabTestcost(100L);

    }

    @Test
    void findAll_whenNoRecord() {

//        Mockito.when(labTestService.getAllLabTests()).thenReturn(Arrays.asList());
//        Assertions.assertEquals(labTestService.getAllLabTests().size(), 0);
//        Mockito.verify(labTestService, Mockito.times(1)).getAllLabTests();

        Mockito.when(labTestRepository.findAll()).thenReturn(Arrays.asList());
        Assertions.assertEquals(labTestRepository.findAll().size(), 0);
        Mockito.verify(labTestRepository, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        LabTest labTestSaved = labTestRepository.save(labTest1);
        Mockito.verify(labTestRepository,Mockito.times(1)).save(labTest1);

    }

    @Test
    void findById_WhenMatch() {

        try{
            ResponseEntity<LabTest> labTestSaved = labTestController.getLabTestById(6L);
            Assertions.assertEquals(labTest1.getLabTestName(),labTestSaved.getBody().getLabTestName());
        } catch (Exception e){

        }
    }

    @Test
    void deleteById_WhenFound() {

     try{
         labTestController.deleteLabTestById(6L);
         Mockito.verify(labTestRepository, Mockito.times(1)).deleteById(6L);
     } catch (Exception e){

     }

    }


}
