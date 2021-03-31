package com.icbt.advancedprogramming.advancedprogramming.Service;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import com.icbt.advancedprogramming.advancedprogramming.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Autowired
    LabTestRepository labTestRepository;

    public LabTest saveLabTest(LabTest labTest){
        return labTestRepository.save(labTest);
    }

    public List<LabTest> getAllLabTests(){
        return labTestRepository.findAll();
    }

    public LabTest getLabTestById(Long labTestId){
        return labTestRepository.findByLabTestId(labTestId);
    }

}
