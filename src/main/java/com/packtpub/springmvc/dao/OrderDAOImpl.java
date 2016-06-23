package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Order;
import com.packtpub.springmvc.model.OrderedItem;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;

@Repository
public class OrderDAOImpl implements OrderDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	

	@Override
	public boolean addOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		return true;
	}



	@Override
	public boolean addOrderedItem(OrderedItem item) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(item);
		return true;
	}



	@Override
	public List<Order> allOrders(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Order ord WHERE ord.user.id = :id");
		query1.setParameter("id", user.getId());
		List<Order> orderList = query1.list();
        return orderList;
	}



	@Override
	public OrderedItem findOrderedItem(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM OrderedItem oi WHERE oi.id = :string_lname");
		
		query1.setParameter("string_lname", id);
		List<OrderedItem> userList = query1.list();
		OrderedItem tk = null;
		for (OrderedItem users : userList) {
			tk = users;
		}
		return tk;
	}



	@Override
	public Order findOrder(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Order oo WHERE oo.id = :string_lname");
		
		query1.setParameter("string_lname", id);
		List<Order> userList = query1.list();
		Order tk = null;
		for (Order users : userList) {
			tk = users;
		}
		return tk;
	}



	@Override
	public void updateOrder(Order ord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(ord);
		
	}

}
