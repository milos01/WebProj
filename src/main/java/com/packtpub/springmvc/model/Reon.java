package com.packtpub.springmvc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Reon")
public class Reon {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int reon_num;
	
	@ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurant_id")
	@NotNull
	private Restaurant restaurant;
	
	@ManyToMany(mappedBy="reons")
	private Set<Staff> staffsReon;

	@OneToMany(mappedBy="reon_id", cascade=CascadeType.ALL) 
	private Set<TableOne> tables;
	
	public Set<Staff> getStaffsReon() {
		return staffsReon;
	}

	public void setStaffsReon(Set<Staff> staffsReon) {
		this.staffsReon = staffsReon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReon_num() {
		return reon_num;
	}

	public void setReon_num(int reon_num) {
		this.reon_num = reon_num;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	

	public Set<TableOne> getTables() {
		return tables;
	}

	public void setTables(Set<TableOne> tables) {
		this.tables = tables;
	}

	@Override
	public String toString() {
		return "Reon [id=" + id + ", reon_num=" + reon_num + ", restaurant=" + restaurant + ", staffsReon=" + staffsReon
				+ "]";
	}
	
	
}
