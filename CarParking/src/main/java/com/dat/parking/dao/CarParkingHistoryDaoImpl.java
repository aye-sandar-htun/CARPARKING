package com.dat.parking.dao;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.CarParkingHistory;

@Repository("carParkingHistoryDao")
public class CarParkingHistoryDaoImpl implements CarParkingHistoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	Session session;
	@Override
	public void persistInformation(CarParkingHistory carParkingHistory) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(carParkingHistory);
	}
	@Override
	public List<CarParkingHistory> carHistory() {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		List<CarParkingHistory> history=session.createQuery("from CarParkingHistory").list();
		for(CarParkingHistory h:history) {

		}
		return history;
	}
	@Override
	public List dateList() {
		// TODO Auto-generated method stub
		session = this.sessionFactory.getCurrentSession(); 
		List dateList = new LinkedList(new LinkedHashSet(session.createQuery("select date from CarParkingHistory").list()));	
		return dateList;
	}
	@Override
	public List<CarParkingHistory> searchByFloor(String floor) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where floor=:floor";
		Query query=session.createQuery(hql);
		query.setParameter("floor", floor);
		List historyList= query.list();
		return historyList;
	}
	@Override
	public List<CarParkingHistory> searchByBuilding(String building) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where building=:building";
		Query query=session.createQuery(hql);
		query.setParameter("building", building);
		List historyList= query.list();
		return historyList;
	}
	@Override
	public List<CarParkingHistory> searchByBuildingFloor(String building, String floor) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where building=:building AND floor=:floor";
		Query query=session.createQuery(hql);
		query.setParameter("building", building).setParameter("floor", floor);
		List historyList= query.list();
		return historyList;
	}
}