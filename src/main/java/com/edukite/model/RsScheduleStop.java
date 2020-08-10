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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rs_schedule_stop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsScheduleStop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "schedule_id")
	private Integer scheduleId;

	@Column(name = "stop_id")
	private Integer stopId;

	@Column(name = "monitor_id")
	private Integer monitorId;

	@Column(name = "stop_number")
	private Integer stopNumber;

	@Column(name = "plan_arrival_time")
	private LocalDateTime planArrivalTime;
	@Column(name = "plan_departure_time")
	private LocalDateTime planDepartureTime;
	@Column(name = "arrival_status_id")
	private Integer arrivalStatusId;

	@Column(name = "arrival_time")
	private LocalDateTime arrivalTime;
	@Column(name = "departure_time")
	private LocalDateTime departureTime;
	@Column(name = "modify_date")
	private LocalDateTime modifyDate;



}
