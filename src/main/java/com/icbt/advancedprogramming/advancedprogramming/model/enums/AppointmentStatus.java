package com.icbt.advancedprogramming.advancedprogramming.model.enums;

public enum AppointmentStatus {

    REQUESTED("REQUESTED"),
    DONE("DONE");

    private String appointmentStatus;

    AppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

}
