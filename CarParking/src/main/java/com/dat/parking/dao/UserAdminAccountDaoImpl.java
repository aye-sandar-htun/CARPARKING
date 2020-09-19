package com.dat.parking.dao;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.UserAdminAccount;
@Repository("userAdminAccountDao")
public class UserAdminAccountDaoImpl implements UserAdminAccountDao{
	@Autowired
	private SessionFactory sessionFactory;
	Session session;
	
	@Override
	public void persistInformation(UserAdminAccount userAdminAccount) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(userAdminAccount);

	}

	@Override
	public List checkAccount(String name, String password) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
			String hql="select id from UserAdminAccount where name=:name AND password=:password";
			Query query=session.createQuery(hql);
			query.setParameter("name", name).setParameter("password", password);
			List id=query.list();
			return id;
	}


}
