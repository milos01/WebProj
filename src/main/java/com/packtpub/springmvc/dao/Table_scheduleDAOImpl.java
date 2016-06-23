package com.packtpub.springmvc.dao;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Reservation;
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
	public List<Table_schedule> table_schedule_list(String res_from, String from, String to, int pepleNum) {
//		System.err.println(pepleNum);
		String [] timeParts = from.split(":");
		String hour = timeParts[0];
		String minute = timeParts[1];
		
		String [] timeToParts = to.split(":");
		String hourTo = timeToParts[0];
		String minuteTo = timeToParts[1];
		
		String [] dateParts = res_from.split("-");
		int year = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int day = Integer.parseInt(dateParts[2]);
		
		System.err.println(year + " " + month + " "+ day);
////		Time now = new Time(Calendar.getInstance().getTime().getTime());
		
		Time fromTime = new Time(Integer.parseInt(hour), Integer.parseInt(minute),00);
		Time toTime = new Time(Integer.parseInt(hourTo), Integer.parseInt(minuteTo),00);
		Date dateRes = new Date(116,month-1,day);
		System.err.println(dateRes.getTime());
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Table_schedule ts WHERE (((ts.reserved_from >:string_from and ts.reserved_from <:string_to) and ts.date =:string_date) or ((ts.reserved_to >:string_from and ts.reserved_to <:string_to) and ts.date =:string_date)) or ((ts.reserved_from <:string_from and ts.reserved_to >:string_to) and ts.date =:string_date)  ORDER BY ts.table.id ASC");
		query1.setParameter("string_from", fromTime);
		query1.setParameter("string_to", toTime);
		query1.setParameter("string_date", dateRes);
		List<Table_schedule> tables = query1.list();
		System.err.println(tables.size());
		return tables;
	}

	@Override
	public List<TableOne> allTables(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM TableOne to WHERE to.reon_id.restaurant.id =:string_id");
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
	@Override
	public boolean addTableSchedule(Table_schedule ts) {
		Session session = sessionFactory.getCurrentSession();
			System.out.println(ts.toString());
			session.persist(ts);
			return true;
		
		
		
	}
	@Override
	public TableOne findTableOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM TableOne to WHERE to.id =:string_id");
		query1.setParameter("string_id", id);
		TableOne tt = null;
		List<TableOne> tables = query1.list();
		for(TableOne to:tables){
			tt = to;
		}
	
		return tt;
	}
	@Override
	public boolean addReservarion(Reservation res) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(res);
		return true;
	}
	

}
