package com.edukite.service.impl;

import com.edukite.constant.EdukiteEnum;
import com.edukite.dto.*;
import com.edukite.model.RsScheduleStop;
import com.edukite.model.RsScheduleStopStudent;
import com.edukite.model.Schedule;
import com.edukite.repository.RsScheduleStopResponsitory;
import com.edukite.repository.RsScheduleStopStudentResponsitory;
import com.edukite.repository.ScheduleResponsitory;
import com.edukite.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dinhanhthai on 26/07/2020.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleResponsitory scheduleResponsitory;
    @Autowired
    RsScheduleStopResponsitory rsScheduleStopResponsitory;
    @Autowired
    RsScheduleStopStudentResponsitory rsSSStudentResponsitory;

    @Override
    public List<Schedule> getMySchedule(Integer userId) {
        return scheduleResponsitory.getMySchedule(userId);
    }

    @Override
    public List<ScheduleDto> getMyScheduleDto(Integer userId) {
        return scheduleResponsitory.getMyScheduleDto(userId);
    }

    @Override
    public List<ScheduleBusDto> getMyScheduleBusStopDto(Integer userId) {
        return scheduleResponsitory.getMyScheduleBusStopDto(userId);
    }

    @Override
    public List<NextBusStopDto> getMyScheduleNextStopDto(Integer userId) {
        return scheduleResponsitory.getMyScheduleNextStopDto(userId);
    }

    @Override
    public List<StudentCheckDto> getListStudentsBySchedule(Integer scheduleid, Integer arrivalId) {
        return scheduleResponsitory.getScheduleStudent(scheduleid, arrivalId);
    }

    @Override
    public List<StudentBusDto> getBusStudentsBySchedule(Integer scheduleid, Integer stopId) {
        return scheduleResponsitory.getScheduleStudentBus(scheduleid, stopId);
    }

    @Override
    public List<Integer> busCheckin(Integer userId, BusCheckinDto busCheckinDto) {
        List<RsScheduleStop> lst = rsScheduleStopResponsitory.findByScheduleIdAndMonitorIdAndStopId(busCheckinDto.getScheduleId(), userId, busCheckinDto.getBusId());

        if (lst != null && lst.size() > 0) {
            for (RsScheduleStop rsScheduleStop : lst) {
                if (busCheckinDto.getTypeId() == EdukiteEnum.BUS_STOP_STATUS.ARRIVED.getValue()) {
                    rsScheduleStop.setArrivalStatusId(EdukiteEnum.BUS_STOP_STATUS.ARRIVED.getValue());
                    rsScheduleStop.setArrivalTime(LocalDateTime.now());
                } else if (busCheckinDto.getTypeId() == EdukiteEnum.BUS_STOP_STATUS.LEFT.getValue()) {
                    rsScheduleStop.setArrivalStatusId(EdukiteEnum.BUS_STOP_STATUS.LEFT.getValue());
                    rsScheduleStop.setDepartureTime(LocalDateTime.now());
                } else if (busCheckinDto.getTypeId() == EdukiteEnum.BUS_STOP_STATUS.SKIPPED.getValue()) {
                    rsScheduleStop.setArrivalStatusId(EdukiteEnum.BUS_STOP_STATUS.SKIPPED.getValue());
                }
                rsScheduleStop.setModifyDate(LocalDateTime.now());
            }
            rsScheduleStopResponsitory.saveAll(lst);
            return Arrays.asList(busCheckinDto.getScheduleId());
        }
        return new ArrayList<>();
    }

    @Override
    public List<StudentCheckedDto> checkinStudent(Integer scheduleid, List<StudentCheckedDto> students, Integer checkType) {
        List<RsScheduleStop> rsScheduleStops = rsScheduleStopResponsitory.findByScheduleId(scheduleid);
        if (rsScheduleStops == null || rsScheduleStops.size() <= 0 || students == null || students.size() <= 0)
            return null;

        List<Integer> lstStdId = students.stream().map(StudentCheckedDto::getStudentId).collect(Collectors.toList());
        System.out.println(lstStdId.size() + "  TESTTTTTT " + students.size());
        List<StudentCheckedDto> studentCheckedDtos = new ArrayList<>();
        List<RsScheduleStopStudent> tmp = new ArrayList<>();
        for (RsScheduleStop rsScheduleStop : rsScheduleStops) {
            List<RsScheduleStopStudent> studentStop = rsSSStudentResponsitory.findByRsScheduleStopIdAndStudentIdInAndCheckTypeId(rsScheduleStop.getId(), lstStdId, checkType);
            System.out.println("haaaaaaaa " + studentStop.size());
            if (studentStop != null && studentStop.size() > 0)
                tmp.addAll(studentStop);
        }

        if (tmp.size() > 0) {
            for (RsScheduleStopStudent rs : tmp) {
                StudentCheckedDto studentCheckedDto = new StudentCheckedDto();
                for (StudentCheckedDto student : students) {
                    if (rs.getStudentId() == student.getStudentId()) {
                        rs.setCheckStatusId(student.getCheckStatus());
                        rs.setCheckTime((LocalDateTime.now()));
                        rs.setCheckTypeId(checkType);
                        if (student.getCheckStatus() == EdukiteEnum.STUDENT_CHECK_STATUS.ABSENT.getValue()) {
                            rs.setAbsenceReason(student.getDesc());
                        }

                        studentCheckedDto.setStudentId(rs.getStudentId());
                        studentCheckedDto.setDesc(student.getDesc());
                        studentCheckedDtos.add(studentCheckedDto);
                        continue;
                    } else {

                    }
                }
            }

            rsSSStudentResponsitory.saveAll(tmp);

        }


        return studentCheckedDtos;
    }
}
