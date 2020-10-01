package com.dat.parking.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dat.parking.model.CarParkingHistory;

import javassist.bytecode.Descriptor.Iterator;

@Repository("carParkingHistoryDao")
public class CarParkingHistoryDaoImpl implements CarParkingHistoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	Session session;
	
	public void persistInformation(CarParkingHistory carParkingHistory) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(carParkingHistory);
	}
	
	public List<CarParkingHistory> carHistory() {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="FROM CarParkingHistory WHERE date >= date_trunc('month',CURRENT_DATE)";
		Query query=session.createQuery(hql);
		List history= query.list();
		return history;
	}
	
	public List dateList() {
		// TODO Auto-generated method stub
		session = this.sessionFactory.getCurrentSession(); 
		List dateList = new LinkedList(new TreeSet(session.createQuery("select date from CarParkingHistory").list()));	
		return dateList;
	}
	
	
	
	
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		 session=this.sessionFactory.getCurrentSession();
			String hql="select floorName from CarParking where buildingName=:buildingName";
			Query query=session.createQuery(hql);
			query.setParameter("buildingName", buildingName);
			List floorList= new LinkedList(new TreeSet(query.list()));
			return floorList;
	}
	
	public List slotLists(String floorName, String buildingName) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="select slot from CarParking where floorName=:floorName AND buildingName=:buildingName order by building_id";
		Query query=session.createQuery(hql);
		query.setParameter("floorName", floorName).setParameter("buildingName", buildingName);
		List slotList= query.list();	
		return slotList;
	}

	
	
	
	
	 public List checkFreeSlot(String slot,String floor,String building,Timestamp exitTime) {
		 session=this.sessionFactory.getCurrentSession();
		 String sql="select id from CarParkingHistory where slot=:slot and floor=:floor and building=:building and exitTime=null";
		 		
		 Query q=session.createQuery(sql);
		 q.setParameter("slot", slot).setParameter("floor",floor).setParameter("building",building).setParameter("exitTime",exitTime);
		 List s=q.list();
		 return s;
	 }
	 
	 
	 //CheckExitingCar
	 public List checkExitingCar(String carNumber,Timestamp exitTime) {
		 session=this.sessionFactory.getCurrentSession();
		 String sql="select id from CarParkingHistory where carNumber=:carNumber and exitTime=null";
		 Query q=session.createQuery(sql);
		 q.setParameter("carNumber",carNumber);
		 List s=q.list();
		 return s;
	 }
	

	
	public List<CarParkingHistory> showCurrent(Date date) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where date=:date";
		Query query=session.createQuery(hql);
		query.setParameter("date", date);
		List todayList= query.list();	
		return todayList;
	}
	
	
	public List<CarParkingHistory> searchByCarNumber(Date date,String carNumber) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where date=:date AND carNumber=:carNumber";
		Query query=session.createQuery(hql);
		query.setParameter("date", date).setParameter("carNumber", carNumber);
		List todayList= query.list();	
		return todayList;
	}
	

	
	
	
	  public CarParkingHistory findById(int id) 
	  { 
	  return (CarParkingHistory)sessionFactory.getCurrentSession().get(CarParkingHistory.class,id); 
	  } 
	 
	  
	  public void deleteCarHistory(CarParkingHistory cars) {
	  sessionFactory.getCurrentSession().delete(cars); 
	  }
	 

	
	public void addExitTime(String building, String floor, String slot,Timestamp exitTime) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="update CarParkingHistory set exitTime=:exitTime where building=:building AND floor=:floor AND slot=:slot";
		Query query=session.createQuery(hql);
		query.setParameter("exitTime",exitTime).setParameter("building", building).setParameter("floor", floor).setParameter("slot", slot);
		query.executeUpdate();
	}

	@Override
	public List<CarParkingHistory> carHistoryForSelectedDate(Date date) {
		// TODO Auto-generated method stub
		session=this.sessionFactory.getCurrentSession();
		String hql="from CarParkingHistory where date=:date";
		Query query=session.createQuery(hql);
		query.setParameter("date", date);
		List selectedCarList= query.list();	
		return selectedCarList;
	}
	
	
	//delete car parking history
	public CarParkingHistory getById(int id) {
		session=sessionFactory.getCurrentSession();
		Transaction tran=session.beginTransaction();
		List<?> list=session.createQuery("from CarParkingHistory where id=?").setParameter(0, id).list();
		tran.commit();
		return (CarParkingHistory) list.get(0);
	}
	
    public void deleteParkingHistory(List<CarParkingHistory> carhistorys) {
    	session=sessionFactory.openSession();
    	Transaction tx=null;
    	try {
    		tx=session.beginTransaction();
    		for(java.util.Iterator<CarParkingHistory> iterator= carhistorys.iterator();iterator.hasNext();) {
    			CarParkingHistory carhistory=(CarParkingHistory) iterator.next();
    			session.delete(carhistory);
    		}
    		tx.commit();
    	}catch(HibernateException e) {
    		if(tx!=null) {
    			tx.rollback();
    			e.printStackTrace();
    		}
    	}
    	finally {
			session.close();
		}

    }
}
