package com.icbt.advancedprogramming.advancedprogramming.model.entity;

import com.icbt.advancedprogramming.advancedprogramming.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "abc_lab_employee")
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator="ABC_LAB_EMPLOYEE_MAP_SEQ")
    @SequenceGenerator(name="ABC_LAB_EMPLOYEE_MAP_SEQ", sequenceName="ABC_LAB_EMPLOYEE_MAP_SEQ", allocationSize=1)
    private Long userId;

    private String staffType;
    private String userName;
    private String employeeAcceptStatus;

    public Employee() {
    }
}
