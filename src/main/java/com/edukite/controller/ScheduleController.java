package com.edukite.controller;

import com.edukite.dto.*;
import com.edukite.helper.ResponseBuilder;
import com.edukite.service.ScheduleService;
import com.edukite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseConttroller {

    @Autowired
    UserService userService;
    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/me")
    public ResponseEntity<ResponseBuilder<List<ScheduleDto>>> getMySchedule() {

        return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.getMyScheduleDto(_USER.getId())));
    }

    @GetMapping("/busstop")
    public ResponseEntity<ResponseBuilder<List<ScheduleBusDto>>> getScheduleBusStop(@RequestParam("scheduleId") String scheduleId,
                                                                                    @RequestParam("monitorId") String monitorId) {

        return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.getMyScheduleBusStopDto(_USER.getId())));

    }

    @PutMapping("/busstop")
    public ResponseEntity<ResponseBuilder<List<Integer>>> getScheduleBusStop(@RequestBody BusCheckinDto busCheckinDto) {
        if(busCheckinDto != null) {
            return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.busCheckin(_USER.getId(), busCheckinDto)));
        }

        return ResponseEntity.ok(ResponseBuilder.buildApplicationException("not correct", 400));
    }

    @GetMapping("/nextstop")
    public ResponseEntity<ResponseBuilder<List<NextBusStopDto>>> getScheduleNextBusStop(@RequestParam("scheduleId") String scheduleId,
                                                                                        @RequestParam("monitorId") String monitorId) {
        return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.getMyScheduleNextStopDto(_USER.getId())));
    }


    @GetMapping("/students")
    public ResponseEntity<ResponseBuilder<List<StudentCheckDto>>> getScheduleStudents(@RequestParam("scheduleId") Integer scheduleId, @RequestParam(value = "arrivalId", required = false) Integer arrivalId) {
        if (arrivalId == null)
            arrivalId = 0;
        return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.getListStudentsBySchedule(scheduleId, arrivalId)));
    }

    @GetMapping("/studentsbus")
    public ResponseEntity<ResponseBuilder<List<StudentBusDto>>> getScheduleBusStudents(@RequestParam("scheduleId") Integer scheduleId, @RequestParam("stopId") Integer stopId) {
        return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.getBusStudentsBySchedule(scheduleId, stopId)));
    }


    @PostMapping("/checkin")
    public ResponseEntity<ResponseBuilder<List<StudentCheckedDto>>> checkinStudent(@RequestBody List<StudentCheckedDto> students, @RequestParam("scheduleId") Integer scheduleId, @RequestParam(value = "checkType", required = false) Integer checkType) {
        if (checkType == null)
            checkType = 1;
        if (checkType == 1 || checkType == 2) {
            return ResponseEntity.ok(ResponseBuilder.buildResponse(scheduleService.checkinStudent(scheduleId, students, checkType)));
        }
        return ResponseEntity.ok(ResponseBuilder.buildApplicationException("Checktype is not correct", 400));

    }


}
