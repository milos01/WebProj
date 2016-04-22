package com.packtpub.springmvc.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	
	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "role_id")
	@NotNull
	private Role role;
	
	@NotNull
	private Date birth_date;

	//@NotBlank(message = "Please select a cache name.")
	private String con_num;
	
	@NotNull
	private int shoe_num;
	
	@ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id")
	@NotNull
	private Restaurant restaurant;
	
	
	@Column(length = 60)
	private String password;
	
	@Column(name = "picture_path", columnDefinition = "varchar(15) default 'def.jpg'")
	@NotBlank
	private String picture;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getCon_num() {
		return con_num;
	}

	public void setCon_num(String con_num) {
		this.con_num = con_num;
	}

	public int getShoe_num() {
		return shoe_num;
	}

	public void setShoe_num(int shoe_num) {
		this.shoe_num = shoe_num;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
