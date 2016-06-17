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
@Table(name = "ReonTypes")
public class ReonTypes {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="ReonTypes_reon",joinColumns=@JoinColumn(name="reonType_id"),inverseJoinColumns=@JoinColumn(name="reon_id"))
	private Set<Reon> reoni;

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

	public Set<Reon> getReoni() {
		return reoni;
	}

	public void setReoni(Set<Reon> reoni) {
		this.reoni = reoni;
	}
	
	
}
