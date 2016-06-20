package com.packtpub.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Milos Andric
 *
 */

@Entity
@Table(name = "Reservation")
public class Reservation {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@NotNull
	private Restaurant res_restaurant;
	
	@ManyToOne(targetEntity = Table_schedule.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "table_schedule_id")
	@NotNull
	private Table_schedule table_schedule;
	
	@NotNull
	private int people_num;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRes_restaurant() {
		return res_restaurant;
	}

	public void setRes_restaurant(Restaurant res_restaurant) {
		this.res_restaurant = res_restaurant;
	}

	public Table_schedule getTable_schedule() {
		return table_schedule;
	}

	public void setTable_schedule(Table_schedule table_schedule) {
		this.table_schedule = table_schedule;
	}

	public int getPeople_num() {
		return people_num;
	}

	public void setPeople_num(int people_num) {
		this.people_num = people_num;
	}
	
	
}
