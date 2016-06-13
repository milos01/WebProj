package com.packtpub.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Food_list_item")
public class FoodListItem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int quantity;
	
	//@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL)
	@ManyToOne(targetEntity = FoodItem.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fooditem_id")
	@NotNull
	private FoodItem fooditem;
	
	@ManyToOne(targetEntity = GrocaryList.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "grocaryList_id")
	@NotNull
	private GrocaryList grocaryList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public FoodItem getFooditem() {
		return fooditem;
	}

	public void setFooditem(FoodItem fooditem) {
		this.fooditem = fooditem;
	}

	public GrocaryList getGrocaryList() {
		return grocaryList;
	}

	public void setGrocaryList(GrocaryList grocaryList) {
		this.grocaryList = grocaryList;
	}
	
	
}
