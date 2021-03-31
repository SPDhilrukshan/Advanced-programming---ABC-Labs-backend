package com.icbt.advancedprogramming.advancedprogramming.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String contactNumber;
    private String NIC;
    private String address;
    private String gender;
    private String maritalStatus;
    private String nationality;
    private String password;


    public Person() {
    }
}
