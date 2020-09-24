package com.dat.parking.dao;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
	Session session;
	
	
	
	@Override
	public void persistInformation(CarParking carParking) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(carParking);
	}
	@Override
	public List buildingLists() {
		// TODO Auto-generated method stub
		 session = this.sessionFactory.getCurrentSession(); 
		List buildingList = new LinkedList(new LinkedHashSet(session.createQuery("select buildingName from CarParking").list()));	
		return buildingList;
	}
	@Override
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		String hql="select floorName from CarParking where buildingName=:buildingName";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName);
		List floorList= new LinkedList(new LinkedHashSet(query.list()));
		return floorList;
	}
	@Override
	public List slotLists(String floorName,String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		String hql="select slot from CarParking where floorName=:floorName AND buildingName=:buildingName";
		Query query=session.createQuery(hql);
		query.setParameter("floorName", floorName).setParameter("buildingName", buildingName);
		List slotList= query.list();	
		return slotList;
	}
	@Override
	public void deleteBuilding(String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName);
	    query.executeUpdate();
		

	}
	@Override
	public void deleteFloor(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName AND floorName=:floorName";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName).setParameter("floorName", floorName);
	     query.executeUpdate();
		
	}
	@Override
	public void deleteSlot(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
		 String hql="delete from CarParking where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
	     Query query=session.createQuery(hql);
	     query.setParameter("buildingName",buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
	     query.executeUpdate();
	}
	
	
	
	public List buildingList(String buildingName) {
		session=this.sessionFactory.getCurrentSession();
		String sql="select buildingName from CarParking where buildingName=:buildingName";
		Query q=session.createQuery(sql);
		q.setParameter("buildingName", buildingName);
		List result=q.list();
		return result;
	}
	@Override
	public void updateStatus(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="update CarParking set status='occupied' where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		query.executeUpdate();
	}
	@Override
	public void updateStatusAvailable(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="update CarParking set status='available' where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		query.executeUpdate();
	}
	@Override
	public String getStatus(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="select status from CarParking where buildingName=:buildingName AND floorName=:floorName AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("buildingName", buildingName).setParameter("floorName", floorName).setParameter("slot", slot);
		return (String)query.uniqueResult();
		
	}
	
	
}
