package com.edukite.dto;

import com.edukite.model.Schedule;

/**
 * Created by dinhanhthai on 27/07/2020.
 */
public class BusCheckinDto  {
    private Integer busId;
    private Integer scheduleId;
    private Integer typeId;
    private String desc;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
