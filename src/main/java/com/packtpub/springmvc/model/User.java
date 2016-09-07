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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author Milos Andric
 *
 */

@Entity
@Table(name = "Users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "First name must not be empty")
	private String firstName;
	
	@NotBlank(message = "Last name must not be empty")
	private String lastName;
	

	@NotBlank(message = "Email must not be empty")
	@Email
	private String email;

	@NotBlank(message = "Password must not be empty")
	@Column(length = 60)
	private String password;


	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "role_id")
	private Role role;
	
	
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	@JsonManagedReference
	private Set<Restaurant> restaurants;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
}
