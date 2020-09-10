package com.dat.parking.dao;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.CarParking;

@Repository("carParkingDao")
public class CarParkingDaoImpl implements CarParkingDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void persistInformation(CarParking carParking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(carParking);
	}
	@Override
	public List buildingList() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession(); 
		List buildingList = new LinkedList(new LinkedHashSet(session.createQuery("select buildingName from CarParking").list()));	
		return buildingList;
	}
	@Override
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		Session session=this.sessionFactory.getCurrentSession();
		String hql="select floorName from CarParking where buildingName=:buildingName";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName);
		List floorList= new LinkedList(new LinkedHashSet(query.list()));
		
		return floorList;
	}
	
	
}
