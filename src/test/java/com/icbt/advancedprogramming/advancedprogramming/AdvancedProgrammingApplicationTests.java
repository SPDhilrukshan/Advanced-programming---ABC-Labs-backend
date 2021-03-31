package com.icbt.advancedprogramming.advancedprogramming;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import com.icbt.advancedprogramming.advancedprogramming.repository.LabTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class AdvancedProgrammingApplicationTests {

    @Autowired
    LabTestRepository labTestRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testLabTestCreate() {
        LabTest labTest = new LabTest();

        labTest.setLabTestdescription("abc");
        labTest.setLabTestcost(90L);
        labTest.setLabTestName("abc");
        LabTest labTestSaved = labTestRepository.save(labTest);
    }
}
