package com.packtpub.springmvc.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Table_schedule")
public class Table_schedule {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int table_id;
	
	@NotNull
	private int reserved_from;
	
	@NotNull
	private int reserved_to;
	
	@NotNull
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public int getReserved_from() {
		return reserved_from;
	}

	public void setReserved_from(int reserved_from) {
		this.reserved_from = reserved_from;
	}

	public int getReserved_to() {
		return reserved_to;
	}

	public void setReserved_to(int reserved_to) {
		this.reserved_to = reserved_to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
