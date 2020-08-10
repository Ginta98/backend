package com.edukite.dto;

import com.edukite.model.Schedule;

/**
 * Created by dinhanhthai on 27/07/2020.
 */
public class ScheduleDto extends Schedule {
    private String name;

    public ScheduleDto(Schedule schedule, String name) {
        super(schedule);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
