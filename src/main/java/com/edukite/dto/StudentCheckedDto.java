package com.edukite.dto;

/**
 * Created by dinhanhthai on 09/08/2020.
 */
public class StudentCheckedDto {
    private Integer studentId;
    private Integer checkStatus;
    private String desc;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
