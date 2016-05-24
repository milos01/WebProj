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
@Table(name = "Menu")
public class Menu {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="desert_menu",joinColumns=@JoinColumn(name="menu_id"),inverseJoinColumns=@JoinColumn(name="desert_id"))
	private Set<Desert> desert;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="maincourse_menu",joinColumns=@JoinColumn(name="menu_id"),inverseJoinColumns=@JoinColumn(name="maincourse_id"))
	private Set<MainCourse> mainCourse;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="appetizer_menu",joinColumns=@JoinColumn(name="menu_id"),inverseJoinColumns=@JoinColumn(name="appetizer_id"))
	private Set<Appetizer> appetizer;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Desert> getDesert() {
		return desert;
	}

	public void setDesert(Set<Desert> desert) {
		this.desert = desert;
	}

	public Set<MainCourse> getMainCourse() {
		return mainCourse;
	}

	public void setMainCourse(Set<MainCourse> mainCourse) {
		this.mainCourse = mainCourse;
	}

	public Set<Appetizer> getAppetizer() {
		return appetizer;
	}

	public void setAppetizer(Set<Appetizer> appetizer) {
		this.appetizer = appetizer;
	}
	
	
	
	
}
