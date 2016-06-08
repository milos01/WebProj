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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TableOne")
public class TableOne {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private int guest_num;
	
	@NotNull
	private int reserved;
	
	@ManyToOne(targetEntity = Reon.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "reon_id")
	@NotNull
	private Reon reon_id;
	
	@OneToMany(mappedBy="table", cascade=CascadeType.ALL)
	private Set<Table_schedule> tables;

	@OneToOne(targetEntity = TablePosition.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false, name = "tableposition_id")
	@NotNull
	private TablePosition tableposition;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGuest_num() {
		return guest_num;
	}

	public void setGuest_num(int guest_num) {
		this.guest_num = guest_num;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	public Set<Table_schedule> getTables() {
		return tables;
	}

	public void setTables(Set<Table_schedule> tables) {
		this.tables = tables;
	}

	public Reon getReon_id() {
		return reon_id;
	}

	public void setReon_id(Reon reon_id) {
		this.reon_id = reon_id;
	}

	public TablePosition getTableposition() {
		return tableposition;
	}

	public void setTableposition(TablePosition tableposition) {
		this.tableposition = tableposition;
	}
	
	
}
