package com.edukite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rs_schedule_stop_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RsScheduleStopStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "rs_schedule_stop_id")
	private Integer rsScheduleStopId;

	@Column(name = "student_id")
	private Integer studentId;
	@Column(name = "pick_up_person_id")
	private Integer pickupPersonId;
	@Column(name = "check_type_id")
	private Integer checkTypeId;
	@Column(name = "check_method_id")
	private Integer checkMethodId;
	@Column(name = "check_status_id")
	private Integer checkStatusId;

//	@UpdateTimestamp
//	@LastModifiedDate
	@Column(name = "check_time")
	private LocalDateTime checkTime;

	@Column(name = "absence_reason")
	private String absenceReason;

	@Column(name = "has_absence_letter")
	private Integer hasAbsenceLetter;
	@Column(name = "absence_letter_reason")
	private String absenceLetterReason;
	@Column(name = "check_turn")
	private Integer checkTurn;
	@Column(name = "is_pkup_by_guardian")
	private Integer isPkupByGuardian;

}
