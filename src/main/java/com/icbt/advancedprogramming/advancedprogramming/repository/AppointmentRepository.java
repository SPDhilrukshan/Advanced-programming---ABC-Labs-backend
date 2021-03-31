package com.icbt.advancedprogramming.advancedprogramming.repository;

import com.icbt.advancedprogramming.advancedprogramming.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    Appointment findByAppointmentId(Long appointmentId);

    List<Appointment> findAllByPatientId(Long patientId);

    List<Appointment> findAllByAppointmentDateBetween(Date startDate, Date endDate);

//    @Query("select * from Appointment a where a.patientId = ?1 and a.labTestId=?2 and a.appointmentId = ?3")
//    List<Appointment> df(Long patientId, Long labTestId, Long aptId);
}
