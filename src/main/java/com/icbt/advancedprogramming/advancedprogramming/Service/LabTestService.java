package com.icbt.advancedprogramming.advancedprogramming.Service;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabTestService {

    public LabTest saveLabTest(LabTest labTest);

    public List<LabTest> getAllLabTests();

    public LabTest getLabTestById(Long labTestId);
}
