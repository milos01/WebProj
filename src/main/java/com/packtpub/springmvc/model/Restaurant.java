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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
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
	private int rate;
	
	//@NotBlank(message = "Please select a cache name.")
	private String desription;
	
	//@NotBlank(message = "Please select a cache name.")
	private String email;
	
	//@NotBlank(message = "Please select a cache name.")
	private String phone;
	
	//@NotBlank(message = "Please select a cache name.")
	private String open_hours;
	
	@NotNull
	private int reon_num;
	
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	private Set<Shift> shifts;
	
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	private Set<Staff> staff;
	
	@OneToMany(mappedBy="res_restaurant", cascade=CascadeType.ALL)
	private Set<Reservation> reservations;

	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	private Set<Reon> reons;
	
	@OneToOne(targetEntity = Menu.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "menu_id")
	@NotNull
	private Menu menu;
	
	@OneToOne(targetEntity = VineCard.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "vine_card_id")
	@NotNull
	private VineCard vineCard;
	
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	private Set<GrocaryList> grocaryList;
	
	public Set<GrocaryList> getGrocaryList() {
		return grocaryList;
	}

	public void setGrocaryList(Set<GrocaryList> grocaryList) {
		this.grocaryList = grocaryList;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<Reon> getReons() {
		return reons;
	}

	public void setReons(Set<Reon> reons) {
		this.reons = reons;
	}

	public Set<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(Set<Shift> shifts) {
		this.shifts = shifts;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
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

	public int getReon_num() {
		return reon_num;
	}

	public void setReon_num(int reon_num) {
		this.reon_num = reon_num;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public VineCard getVineCard() {
		return vineCard;
	}

	public void setVineCard(VineCard vineCard) {
		this.vineCard = vineCard;
	}
}
