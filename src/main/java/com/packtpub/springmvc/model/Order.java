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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OrderItem")
public class Order {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double rateNum;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	
	@ManyToOne(targetEntity = Reservation.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_id")
	@NotNull
	private Reservation reservation;
	
	private int radyOnTime;
	
	private int accepted;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private Set<OrderedItem> orderedItems;

	public Set<OrderedItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(Set<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}
	

	public double getRateNum() {
		return rateNum;
	}

	public void setRateNum(double rateNum) {
		this.rateNum = rateNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getRate() {
		return rateNum;
	}

	public void setRate(double rate) {
		this.rateNum = rate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getRadyOnTime() {
		return radyOnTime;
	}

	public void setRadyOnTime(int radyOnTime) {
		this.radyOnTime = radyOnTime;
	}

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

}
