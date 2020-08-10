package com.edukite.dto;

import com.edukite.model.Schedule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinhanhthai on 27/07/2020.
 */
public class ScheduleBusDto extends Schedule {
    private String stopName;
    private Double longtitude;
    private Double latitude;
    private String icon;
    private String arrival;
    private Integer arrivalId;

    public ScheduleBusDto(Schedule schedule, Integer arrivalId, String stopName, Double longtitude, Double latitude) {
        super(schedule);
        this.stopName = stopName;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.icon = "bus";
        this.arrivalId = arrivalId;
        if(_MAP_BUS_STOP_STATUS.size() == 0){
            _MAP_BUS_STOP_STATUS.put(1, "not-yet");
            _MAP_BUS_STOP_STATUS.put(2, "arrived");
            _MAP_BUS_STOP_STATUS.put(3, "left");
            _MAP_BUS_STOP_STATUS.put(4, "skipped");
        }
    }

    private static Map<Integer, String> _MAP_BUS_STOP_STATUS = new HashMap<>();


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getArrival() {
        if(arrivalId == null)
            return null;

        return _MAP_BUS_STOP_STATUS.get(arrivalId);
    }



    public Integer getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
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
