package com.packtpub.springmvc.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Shift")
public class Shift {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Please select a cache name.")
	private String shift_entry;
	
	@NotNull
	private Time start_shift;
	
	@NotNull
	private Time end_shift;
	
	
	@ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@NotNull
	private Restaurant restaurant;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShift_entry() {
		return shift_entry;
	}

	public void setShift_entry(String shift_entry) {
		this.shift_entry = shift_entry;
	}

	public Time getStart_shift() {
		return start_shift;
	}

	public void setStart_shift(Time start_shift) {
		this.start_shift = start_shift;
	}

	public Time getEnd_shift() {
		return end_shift;
	}

	public void setEnd_shift(Time end_shift) {
		this.end_shift = end_shift;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
