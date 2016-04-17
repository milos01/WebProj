package com.packtpub.springmvc.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Restaurants")
public class Restaurant {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Please select a cache name.")
	private String name;
	
	@NotBlank(message = "Please select a cache name.")
	private String address;
	
	@NotBlank(message = "Please select a cache name.")
	private int rate;
	
	@NotBlank(message = "Please select a cache name.")
	private String desription;
	
	@NotBlank(message = "Please select a cache name.")
	private String email;
	
	@NotBlank(message = "Please select a cache name.")
	private String phone;
	
	@NotBlank(message = "Please select a cache name.")
	private String open_hours;
	
	@OneToMany(mappedBy="restaurant")
	private Set<Staff> staff = new HashSet<Staff>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpen_hours() {
		return open_hours;
	}

	public void setOpen_hours(String open_hours) {
		this.open_hours = open_hours;
	}

	public Set<Staff> getStaff() {
		return staff;
	}

	public void setStaff(Set<Staff> staff) {
		this.staff = staff;
	}
	
}
