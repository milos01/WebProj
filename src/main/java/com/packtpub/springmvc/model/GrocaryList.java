package com.packtpub.springmvc.model;

import java.sql.Date;
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
@Table(name = "Grocary_list")
public class GrocaryList {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	@NotNull
	private Restaurant restaurant;
	
	@NotNull
	private Date GLfrom;
	
	@NotNull
	private Date GLto;
	
	@OneToMany(mappedBy="fooditem", cascade=CascadeType.ALL)
	private Set<FoodListItem> foodListItems;
	
	@OneToMany(mappedBy="grocaryList", cascade=CascadeType.ALL)
	private Set<Offer> offers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<FoodListItem> getFoodListItems() {
		return foodListItems;
	}

	public void setFoodListItems(Set<FoodListItem> foodListItems) {
		this.foodListItems = foodListItems;
	}

	public Date getGLfrom() {
		return GLfrom;
	}

	public void setGLfrom(Date gLfrom) {
		GLfrom = gLfrom;
	}

	public Date getGLto() {
		return GLto;
	}

	public void setGLto(Date gLto) {
		GLto = gLto;
	}
	
	
}
