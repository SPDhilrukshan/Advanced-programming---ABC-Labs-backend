package com.icbt.advancedprogramming.advancedprogramming.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "abc_lab_billing")
public class Billing {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,  generator="ABC_LAB_BILLING_MAP_SEQ")
    @SequenceGenerator(name="ABC_LAB_BILLING_MAP_SEQ", sequenceName="ABC_LAB_BILLING_MAP_SEQ", allocationSize=1)
    private Long billingId;
    private Date billedDate;
    private Long appointmentId;
    private Long patientId;
    private Long billedAmount;
    private String billingType;
}
