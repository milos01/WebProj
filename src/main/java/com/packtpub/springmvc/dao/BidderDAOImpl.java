package com.packtpub.springmvc.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.FoodItem;
import com.packtpub.springmvc.model.FoodListItem;
import com.packtpub.springmvc.model.GrocaryList;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.Offer;

@Repository
public class BidderDAOImpl implements BidderDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addGroceryList(GrocaryList gl) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(gl);
		
	}

	@Override
	public void addFoodItem(FoodItem fi) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(fi);
		
	}

	@Override
	public void addFoodListItem(FoodListItem fli) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(fli);
		
	}

	@Override
	public FoodItem findFoodItem(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM FoodItem u WHERE u.name = :string_email");
		query1.setParameter("string_email", name);
		List<FoodItem> userList = query1.list();
		FoodItem tk = null;
		for (FoodItem users : userList) {
			tk = users;
		}
		return tk;
	}

	@Override
	public List<FoodListItem> findFoodList(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM FoodListItem u WHERE u.grocaryList.id = :string_email");
		query1.setParameter("string_email", id);
		List<FoodListItem> userList = query1.list();
		return userList;
	}

	@Override
	public List<FoodItem> GetAllFoodItems() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM FoodItem");
		List<FoodItem> userList = query1.list();
		return userList;
	}

	@Override
	public GrocaryList findGrocery(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM GrocaryList u WHERE u.id = :string_email");
		query1.setParameter("string_email", id);
		List<GrocaryList> userList = query1.list();
		GrocaryList tk = null;
		for (GrocaryList users : userList) {
			tk = users;
		}
		return tk;
	}

	@Override
	public void createOffer(Offer of) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(of);
		
	}

	@Override
	public Offer getOffer(int grocery_id, int user_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Offer u WHERE u.grocaryList.id = :gros_id AND u.user.id =:user_id");
		query1.setParameter("gros_id", grocery_id);
		query1.setParameter("user_id",user_id);
		List<Offer> userList = query1.list();
		Offer tk = null;
		for (Offer users : userList) {
			tk = users;
		}
		return tk;
	}

	@Override
	public void updateOffer(Offer of) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(of);
	}

	@Override
	public List<Offer> getAllUserOffers(int id_user) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Offer u WHERE u.user.id = :string_email");
		query1.setParameter("string_email", id_user);
		List<Offer> userList = query1.list();
		return userList;
	}

	@Override
	public Offer findOffer(int idOf) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Offer u WHERE u.id = :gros_id");
		query1.setParameter("gros_id", idOf);
		List<Offer> userList = query1.list();
		Offer tk = null;
		for (Offer users : userList) {
			tk = users;
		}
		return tk;
	}

	@Override
	public List<Offer> getOffers(int grocery_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Offer u WHERE u.grocaryList.id = :gros_id");
		query1.setParameter("gros_id", grocery_id);
		List<Offer> userList = query1.list();
		return userList;
	}

}
