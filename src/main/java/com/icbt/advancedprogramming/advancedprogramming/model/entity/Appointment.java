package com.icbt.advancedprogramming.advancedprogramming.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "abc_lab_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator="ABC_LAB_APPOINTMENT_MAP_SEQ")
    @SequenceGenerator(name="ABC_LAB_APPOINTMENT_MAP_SEQ", sequenceName="ABC_LAB_APPOINTMENT_MAP_SEQ", allocationSize=1)
    private Long appointmentId;

    private Date appointmentDate;
    private String appointmentStatus;
    private Long labTestId;
    private Long patientId;

    public Appointment() {
    }
}
