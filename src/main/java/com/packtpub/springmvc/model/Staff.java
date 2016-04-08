package com.packtpub.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Milos Andric
 *
 */

@Entity
@Table(name = "Staff")
public class Staff {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Please select a cache name.")
	private String firstName;

	@NotBlank(message = "Please select a cache name.")
	private String lastName;

	@NotBlank(message = "Please select a cache name.")
	@Email
	private String email;

	@Column(length = 60)
	private String password;

	private boolean enabled;

	private boolean tokenExpired;

}
