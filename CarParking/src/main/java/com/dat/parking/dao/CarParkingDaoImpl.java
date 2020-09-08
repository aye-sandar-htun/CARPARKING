package com.dat.parking.dao;

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

}
