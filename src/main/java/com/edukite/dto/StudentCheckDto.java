package com.edukite.dto;

import com.edukite.model.Schedule;
import com.edukite.model.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dinhanhthai on 27/07/2020.
 */
public class StudentCheckDto extends Student {

    private String arrival;
    private Integer arrivalId;
    private String reason;

    public StudentCheckDto(Student student, Integer arrivalId, String reason) {
        super(student);

        this.arrivalId = arrivalId;
        this.reason = reason;
        if(_MAP_STUDENT_STATUS.size() == 0){
            _MAP_STUDENT_STATUS.put(1, "not-yet");
            _MAP_STUDENT_STATUS.put(2, "checked");
            _MAP_STUDENT_STATUS.put(3, "absent");
            _MAP_STUDENT_STATUS.put(4, "early-checked");
        }
    }

    private static Map<Integer, String> _MAP_STUDENT_STATUS = new HashMap<>();



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getArrival() {
        if(arrivalId == null)
            return null;

        return _MAP_STUDENT_STATUS.get(arrivalId);
    }


    public Integer getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(Integer arrivalId) {
        this.arrivalId = arrivalId;
    }
}
