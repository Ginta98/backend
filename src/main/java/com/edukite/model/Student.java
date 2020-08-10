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
import java.util.Date;
@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "family_id")
	private Integer familyId;

	@Column(name = "relation_to_main_member_id")
	private Integer relationToMainMemberId;
	@Column(name = "student_code")
	private String studentCode;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "nickname")
	private String nickname;
	@Column(name = "gender")
	private String gender;

	@Column(name = "birth_date")
	private Date birthDate;


	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "school_enroll_date")
	private LocalDateTime schoolEnrollDate;

	@Column(name = "school_graduate_date")
	private LocalDateTime getSchoolGraduateDate;
	@Column(name = "profile_picture_id")
	private Integer profilePictureId;

	public String getFullName(){
		return String.format("%s %s %s", lastName, middleName, firstName);
	}

	public Student(Student student){
		org.springframework.beans.BeanUtils.copyProperties(student, this);
	}
}
