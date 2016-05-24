package com.packtpub.springmvc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(mappedBy="menu")
	private Set<Desert> desert;

	@ManyToMany(mappedBy="menu")
	private Set<MainCourse> mainCourse;
	
	@ManyToMany(mappedBy="menu")
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
