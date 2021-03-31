package com.icbt.advancedprogramming.advancedprogramming.model.entity;

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
@Table(name = "abc_lab_lab_test")
public class LabTest {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator="ABC_LAB_LAB_TEST_MAP_SEQ")
    @SequenceGenerator(name="ABC_LAB_LAB_TEST_MAP_SEQ", sequenceName="ABC_LAB_LAB_TEST_MAP_SEQ", allocationSize=1)
    private Long labTestId;

    private String labTestName;
    private String labTestdescription;
    private Long labTestcost;

    public LabTest() {
    }
}
