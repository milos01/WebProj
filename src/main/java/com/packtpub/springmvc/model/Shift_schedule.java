package com.packtpub.springmvc.model;

import java.sql.Date;

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
import javax.validation.constraints.NotNull;
import java.util.Set;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table (name="Shift_schedule")
public class Shift_schedule {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String shift_entry;
	
	@NotNull
	private Date shift_date;
	

//	@JoinTable(name="Shift_schedule_staff",joinColumns=@JoinColumn(name="staff_id"),inverseJoinColumns=@JoinColumn(name="shift_schedule_id"))
//	@ManyToMany(mappedBy="shift_schedule")
	
	@ManyToMany(mappedBy="shift_schedule")
	private Set<Staff> staffs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShift_entry() {
		return shift_entry;
	}

	public void setShift_entry(String shift_entry) {
		this.shift_entry = shift_entry;
	}

	public Date getShift_date() {
		return shift_date;
	}

	public void setShift_date(Date shift_date) {
		this.shift_date = shift_date;
	}

	public Set<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

	@Override
	public String toString() {
		return "Shift_schedule [id=" + id + ", shift_entry=" + shift_entry + ", shift_date=" + shift_date + ", staffs="
				+ staffs + "]";
	}
	
	
	
	
}
