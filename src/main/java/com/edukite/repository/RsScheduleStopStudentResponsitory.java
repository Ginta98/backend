package com.edukite.repository;

import com.edukite.model.RsScheduleStop;
import com.edukite.model.RsScheduleStopStudent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RsScheduleStopStudentResponsitory extends CrudRepository<RsScheduleStopStudent, Integer>  {
    List<RsScheduleStopStudent> findByRsScheduleStopIdAndStudentIdInAndCheckTypeId(Integer rsSSId, List<Integer> studentIds, Integer checkType);

}
