package com.packtpub.springmvc.model;


import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "Restaurants")
public class Restaurant {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Restaurant name must not be empty")
	private String name;
	
	@NotBlank(message = "Restaurant address must not be empty")
	private String address;
	
	//@NotBlank(message = "Please select a cache name.")
	private String city;
	
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String ziroRacun;
	
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pib;
	
	//@NotBlank(message = "Please select a cache name.")
	private String site;
	
	//@NotBlank(message = "Please select a cache name.")
	private String email;
	
	//@NotBlank(message = "Please select a cache name.")
	private String phone;
	
	//@NotBlank(message = "Please select a cache name.")
	private String picture;

	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIdentityReference(alwaysAsId = true)
	@NotNull
	private User user;
	

	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<Event> events;
	
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<Recension> recensions;
	
	
	
	@JsonBackReference
	public User getUser() {
		return user;
	}
	
	

	public Set<Recension> getRecensions() {
		return recensions;
	}



	public void setRecensions(Set<Recension> recensions) {
		this.recensions = recensions;
	}



	public Set<Event> getEvents() {
		return events;
	}



	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZiroRacun() {
		return ziroRacun;
	}

	public void setZiroRacun(String ziroRacun) {
		this.ziroRacun = ziroRacun;
	}

	public int getPib() {
		return pib;
	}

	public void setPib(int pib) {
		this.pib = pib;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
