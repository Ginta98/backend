package com.edukite.repository;

import com.edukite.model.RsScheduleStop;
import com.edukite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RsScheduleStopResponsitory extends JpaRepository<RsScheduleStop, Integer>  {
    List<RsScheduleStop> findByScheduleId(Integer scheduleId);
    List<RsScheduleStop> findByScheduleIdAndMonitorIdAndStopId(Integer scheduleId, Integer monitorId, Integer stopId);
}
