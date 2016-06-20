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

	private boolean enabled;

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	private boolean tokenExpired;
	
	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "role_id")
	private Role role;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Set<Order> orders;

	@ManyToMany
	@JoinTable(name="Friends",
	 joinColumns=@JoinColumn(name="starter_friend"),
	 inverseJoinColumns=@JoinColumn(name="terminal_friend")
	)
	private Set<User> starter_friend;

	@ManyToMany
	@JoinTable(name="Friends",
	 joinColumns=@JoinColumn(name="terminal_friend"),
	 inverseJoinColumns=@JoinColumn(name="starter_friend")
	)
	private Set<User> terminal_friend;
	
	public User() {
		super();
		this.enabled = false;
		this.tokenExpired = false;
	}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<User> getStarter_friend() {
		return starter_friend;
	}

	public void setStarter_friend(Set<User> starter_friend) {
		this.starter_friend = starter_friend;
	}

	public Set<User> getTerminal_friend() {
		return terminal_friend;
	}

	public void setTerminal_friend(Set<User> terminal_friend) {
		this.terminal_friend = terminal_friend;
	}
	
	
}
