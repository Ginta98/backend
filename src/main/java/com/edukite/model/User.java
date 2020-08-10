package com.edukite.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "is_active")
	private Integer isActive;

	@Column(name = "school_id")
	private Integer schoolId;

	@Column(name = "user_type_id")
	private Integer userTypeId;

	@Column(name = "profile_picture_id")
	private Integer profilePictureId;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "expiry_date")
	private Date expiryDate;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "birth_date")
	private Date birthDate;
	@Column(name = "identity_number")
	private String identityNumber;
	@Column(name = "phone")
	private String phone;
	@Column(name = "email")
	private String email;

}
