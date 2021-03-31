package com.icbt.advancedprogramming.advancedprogramming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icbt.advancedprogramming.advancedprogramming.model.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByPatientId(Long patientId);

}
