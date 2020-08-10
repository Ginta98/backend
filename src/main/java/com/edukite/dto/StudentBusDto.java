package com.edukite.dto;

import com.edukite.model.Schedule;
import com.edukite.model.Student;

/**
 * Created by dinhanhthai on 27/07/2020.
 */
public class StudentBusDto extends Student {
    private String stopName;
    private Double longtitude;
    private Double latitude;
    private String icon;
    private String status;
    private Integer statusId;

    public StudentBusDto(Student student, String stopName, Double longtitude, Double latitude) {
        super(student);
        this.stopName = stopName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.icon = "bus";
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
