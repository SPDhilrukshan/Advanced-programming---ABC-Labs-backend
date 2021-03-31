package com.icbt.advancedprogramming.advancedprogramming.model.entity;

import com.icbt.advancedprogramming.advancedprogramming.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "abc_lab_patient")
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator="ABC_LAB_PATIENT_MAP_SEQ")
    @SequenceGenerator(name="ABC_LAB_PATIENT_MAP_SEQ", sequenceName="ABC_LAB_PATIENT_MAP_SEQ", allocationSize=1)
    private Long patientId;
    private String bloodGroup;
    private String occupation;

    public Patient() {
        super();
    }
}
