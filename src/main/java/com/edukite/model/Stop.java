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

@Entity
@Table(name = "stop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "address_id")
	private Integer addressId;

	@Column(name = "stop_name")
	private String stopName;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modify_date")
	private Date modifyDate;



}
