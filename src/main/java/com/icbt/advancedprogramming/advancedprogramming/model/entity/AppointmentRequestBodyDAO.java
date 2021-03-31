package com.icbt.advancedprogramming.advancedprogramming.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequestBodyDAO {
    private Appointment appointment;
    private Long patientId;
}
