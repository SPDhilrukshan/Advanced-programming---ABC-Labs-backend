package com.icbt.advancedprogramming.advancedprogramming.repository;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest,Long> {

   public LabTest findByLabTestId(Long labTestId);
}
