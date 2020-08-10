package com.edukite.service;

import com.edukite.dto.*;
import com.edukite.model.Schedule;
import com.edukite.model.Student;
import com.edukite.model.User;

import java.util.List;

/**
 * Created by dinhanhthai on 26/07/2020.
 */
public interface ScheduleService {
    List<Schedule> getMySchedule(Integer id);
    List<ScheduleDto> getMyScheduleDto(Integer id);
    List<ScheduleBusDto> getMyScheduleBusStopDto(Integer id);
    List<Integer> busCheckin(Integer userId, BusCheckinDto busCheckinDtos);
    List<NextBusStopDto> getMyScheduleNextStopDto(Integer id);
    List<StudentCheckDto> getListStudentsBySchedule(Integer scheduleid, Integer arrivalId);
    List<StudentBusDto> getBusStudentsBySchedule(Integer scheduleid, Integer stopId);
    List<StudentCheckedDto> checkinStudent(Integer scheduleid, List<StudentCheckedDto> students, Integer checkType);
}
