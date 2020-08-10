package com.edukite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule implements Serializable {
	protected static final long serialVersionUID = 1L;

	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Integer id;

	@Column(name = "school_id")
	protected Integer schoolId;

	@Column(name = "bus_id")
	protected Integer busId;

	@Column(name = "schedule_status_id")
	protected Integer scheduleStatusId;

	@Column(name = "creator_id")
	protected Integer creatorId;

	@Column(name = "direction_id")
	protected Integer directionId;

	@Column(name = "monitor_id")
	protected Integer monitorId;

	@Column(name = "schedule_code")
	protected String scheduleCode;

	@Column(name = "schedule_name")
	protected String scheduleName;

	@Column(name = "has_change_request")
	protected Integer changeRequest;

	@Column(name = "plan_departure_time")
	protected LocalDateTime planDepartureTime;
	@Column(name = "plan_arrival_time")
	protected LocalDateTime planArrivalTime;
	@Column(name = "departure_time")
	protected LocalDateTime departureTime;
	@Column(name = "arrival_time")
	protected LocalDateTime arrivalTime;
//	@Column(name = "duration")
//	protected Date duration;
	@Column(name = "departure_id")
	protected Integer departureId;
	@Column(name = "destination_id")
	protected Integer destinationId;
	@Column(name = "incident_type")
	protected Integer incidentType;
	@Column(name = "created_date")
	protected LocalDateTime createdDate;
	@Column(name = "modify_date")
	protected LocalDateTime modifyDate;
	/*@Column(name = "participant_user_id", columnDefinition = "json")
	protected com.mysql.cj.xdevapi.JsonArray  participantUserId;*/
//	@Type(type= "org.coderdreams.hibernate.JsonStringType")
//	@Type(type = "json")
	@Column(name = "participant_user_id" /* ,columnDefinition = "json"*/)
	private String participantUserId;

	public Schedule(Schedule schedule){
		org.springframework.beans.BeanUtils.copyProperties(schedule, this);
	}

}
