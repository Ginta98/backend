package com.edukite.repository;

import com.edukite.dto.*;
import com.edukite.model.Schedule;
import com.edukite.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleResponsitory extends CrudRepository<Schedule, Integer> {
    @Query(value = "from com.edukite.model.Schedule a left join  com.edukite.model.User b on a.monitorId = b.id where a.monitorId = ?1")
    List<Schedule> getMySchedule(Integer userId);

    @Query(value = "select new com.edukite.dto.ScheduleDto(a, b.firstName)  from com.edukite.model.Schedule a " +
                                                            "  left join  com.edukite.model.User b on a.monitorId = b.id " +
                                                            "       where a.monitorId = ?1 order by a.createdDate desc")
    List<ScheduleDto> getMyScheduleDto(Integer userId);

    @Query(value = "select new com.edukite.dto.ScheduleBusDto(a,b.arrivalStatusId, c.stopName, d.longtitude, d.latitude)  from com.edukite.model.Schedule a " +
                                                             "       left join  com.edukite.model.RsScheduleStop b on a.id = b.scheduleId " +
                                                            "   left join      com.edukite.model.Stop c on b.stopId = c.id" +
                                                            "   left join       com.edukite.model.LkAdress d on c.addressId = d.id" +
                                                            "  where a.monitorId = ?1 order by a.createdDate desc      ")
    List<ScheduleBusDto> getMyScheduleBusStopDto(Integer userId);

    @Query(value = "select new com.edukite.dto.NextBusStopDto(ss.planArrivalTime, s.stopName, s.id, lk.longtitude, lk.latitude) from com.edukite.model.RsScheduleStop ss " +
                                                        " left join  com.edukite.model.Stop s on ss.stopId = s.id " +
                                                        " left join com.edukite.model.Schedule sc on sc.id = ss.scheduleId" +
                                                        " left join com.edukite.model.LkAdress lk on lk.id = s.addressId  " +
                                                        " where ss.monitorId = ?1  and ss.arrivalStatusId = 0 and sc.scheduleStatusId =2 " +
                                                        " order by sc.planArrivalTime asc " +
                                                        "  ")
    List<NextBusStopDto> getMyScheduleNextStopDto(Integer userId);


    @Query(value = "select new com.edukite.dto.StudentCheckDto(s, sss.checkStatusId, sss.absenceReason )  from com.edukite.model.RsScheduleStop ss " +
                                "  inner join  com.edukite.model.Schedule sc on sc.id = ss.scheduleId " +
                                "  inner join  com.edukite.model.RsScheduleStopStudent sss on sss.rsScheduleStopId =  ss.id " +
                                "  left JOIN  com.edukite.model.Student s on s.id = sss.studentId " +
                                "  where ss.scheduleId = ?1 " +
                                " and (0 = ?2 or sss.checkStatusId = ?2) " +
                                "      and sc.scheduleStatusId = 2")
    List<StudentCheckDto> getScheduleStudent(Integer scheduleId, Integer arrivalId);

    @Query(value = "select  new com.edukite.dto.StudentBusDto(s,  st.stopName, lk.longtitude, lk.latitude)  from com.edukite.model.RsScheduleStop ss " +
            "  inner join  com.edukite.model.Schedule sc on sc.id = ss.scheduleId " +
            "  inner join  com.edukite.model.RsScheduleStopStudent sss on sss.rsScheduleStopId =  ss.id " +
            "  left JOIN  com.edukite.model.Student s on s.id = sss.studentId " +
            " LEFT JOIN com.edukite.model.Stop st on st.id = ss.stopId " +
            "  LEFT JOIN com.edukite.model.LkAdress lk on lk.id = st.addressId " +
            "  where ss.scheduleId = ?1 " +
            "    and  (0 = ?2 or st.id = ?2 ) " +
            "      and sc.scheduleStatusId = 2")
    List<StudentBusDto> getScheduleStudentBus(Integer scheduleId, Integer stopId);

}
