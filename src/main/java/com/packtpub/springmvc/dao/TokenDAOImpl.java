package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

@Repository
public class TokenDAOImpl implements TokenDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public VerificationToken findByToken(String token) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM VerificationToken vt WHERE vt.token = :string_token");
		query1.setParameter("string_token",token);
		System.out.println(query1.getFirstResult());
		List<VerificationToken> tokenList = query1.list();
		VerificationToken tk = null;
		for ( VerificationToken tokens: tokenList ) {
		   tk = tokens;
		}
		return tk; 
	}

	@Override
	public VerificationToken findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveVerificationTokenForUser(VerificationToken Token) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(Token);
//				
	}

}
