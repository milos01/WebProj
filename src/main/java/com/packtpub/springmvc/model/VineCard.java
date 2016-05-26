package com.packtpub.springmvc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Vine_card")
public class VineCard {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Vine_card_alcoholic",joinColumns=@JoinColumn(name="vine_card_id"),inverseJoinColumns=@JoinColumn(name="alcoholic_id"))
	private Set<AlcoholicDrink> alcoholicDrink;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Vine_card_nonAlcoholic",joinColumns=@JoinColumn(name="vine_card_id"),inverseJoinColumns=@JoinColumn(name="nonalcoholic_id"))
	private Set<NonAlcoholicDrink> nonAlcoholicDrink;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<AlcoholicDrink> getAlcoholicDrink() {
		return alcoholicDrink;
	}

	public void setAlcoholicDrink(Set<AlcoholicDrink> alcoholicDrink) {
		this.alcoholicDrink = alcoholicDrink;
	}

	public Set<NonAlcoholicDrink> getNonAlcoholicDrink() {
		return nonAlcoholicDrink;
	}

	public void setNonAlcoholicDrink(Set<NonAlcoholicDrink> nonAlcoholicDrink) {
		this.nonAlcoholicDrink = nonAlcoholicDrink;
	}
	
	
	
	
	
}
