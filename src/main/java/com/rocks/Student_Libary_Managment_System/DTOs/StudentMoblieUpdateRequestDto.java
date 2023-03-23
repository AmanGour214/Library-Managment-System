package com.rocks.Student_Libary_Managment_System.DTOs;

public class StudentMoblieUpdateRequestDto {

    private int id;
    private String mobileNo;

    public StudentMoblieUpdateRequestDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
