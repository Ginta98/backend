package com.edukite.dto;

import com.edukite.model.Schedule;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
/**
 * Created by dinhanhthai on 27/07/2020.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NextBusStopDto  implements Serializable {
    protected static final long serialVersionUID = 1L;
//    @com.fasterxml.jackson.annotation.JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

    private String name;
    private Integer stopId;
    private Double longtitude;
    private Double latitude;


}
