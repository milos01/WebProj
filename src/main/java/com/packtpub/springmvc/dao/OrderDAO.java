package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Order;
import com.packtpub.springmvc.model.OrderedItem;
import com.packtpub.springmvc.model.User;

public interface OrderDAO {
	public boolean addOrder(Order order);
	
	public boolean addOrderedItem(OrderedItem item);
	
	public List<Order> allOrders(User user);
	
	public OrderedItem findOrderedItem(int id);
	
	public Order findOrder(int id);
	
	public void updateOrder(Order ord);
}
