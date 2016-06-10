package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.TablePosition;
import com.packtpub.springmvc.model.Table_schedule;
@Repository
public class Table_scheduleDAOImpl implements Table_scheduleDAO {
	
private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	@Override
	public List<Table_schedule> table_schedule_list(int from, int to, int pepleNum) {
		System.err.println(pepleNum);
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Table_schedule ts WHERE ((ts.reserved_from >:string_from and ts.reserved_from <:string_to) or (ts.reserved_to >:string_from and ts.reserved_to <:string_to)) or (ts.reserved_from <:string_from and ts.reserved_to >:string_to) ORDER BY ts.table.id ASC");
		query1.setParameter("string_from", from);
		query1.setParameter("string_to", to);
		List<Table_schedule> tables = query1.list();
		return tables;
	}

	@Override
	public List<TableOne> allTables(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM TableOne to WHERE to.restaurant_id =:string_id");
		query1.setParameter("string_id", id);
		List<TableOne> tables = query1.list();
		return tables;
	}
	@Override
	public void updateTablePosition(TablePosition tp) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(tp);
		
	}
	@Override
	public void addNeWTablePosition(TablePosition tp) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tp);
		
	}
	@Override
	public void addNewTable(TableOne to) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(to);
		
	}
	@Override
	public List<TablePosition> tablePositions() {

		return null;
	}
	@Override
	public void removeTalbePosition(TablePosition tp) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete FROM TablePosition tp where tp.id=:id");
		query.setParameter("id", tp.getId());
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	@Override
	public void removeTable(TableOne to) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete FROM TableOne tp where tp.id=:id");
		query.setParameter("id", to.getId());
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	@Override
	public TableOne findTable(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM TableOne to WHERE to.tableposition_id =:string_id");
		query1.setParameter("string_id", id);
		TableOne tt = null;
		List<TableOne> tables = query1.list();
		for(TableOne to:tables){
			tt = to;
		}
		return tt;
	}
	@Override
	public TablePosition findTablePosition(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM TablePosition to WHERE to.id =:string_id");
		query1.setParameter("string_id", id);
		TablePosition tt = null;
		List<TablePosition> tables = query1.list();
		for(TablePosition to:tables){
			tt = to;
		}
		return tt;
	}
	@Override
	public void removeTableSchedule(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete FROM Table_schedule tp where tp.id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		tx.commit();
		session.close();
		
	}

}
