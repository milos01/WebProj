package com.packtpub.springmvc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "NonAlcoholic_drink")
public class NonAlcoholicDrink {


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private int price;
	
	private double rate;
	
	private String quantity;
	
	@Column(name = "picture_path")
	@NotBlank
	private String picture;
	
	@ManyToMany(mappedBy="nonAlcoholicDrink")
	private Set<VineCard> vineCard;

	public int getId() {
		return id;
	}
	
	

	public double getRate() {
		return rate;
	}



	public void setRate(double rate) {
		this.rate = rate;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set<VineCard> getVineCard() {
		return vineCard;
	}

	public void setVineCard(Set<VineCard> vineCard) {
		this.vineCard = vineCard;
	}
	
	
}
