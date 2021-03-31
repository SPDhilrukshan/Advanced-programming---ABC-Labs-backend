package com.icbt.advancedprogramming.advancedprogramming.model.enums;

public enum UserType {
    NURSE("NURSE"),
    RECEPTIONIST("RECEPTIONIST"),
    MANAGER("MANAGER");

    private String UserTypeCode;

    UserType(String userTypeCode) {
        UserTypeCode = userTypeCode;
    }

    public String getUserTypeCode() {
        return UserTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        UserTypeCode = userTypeCode;
    }

    @Override
    public String toString(){ return UserTypeCode; }
}
