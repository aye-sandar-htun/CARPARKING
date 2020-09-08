package com.dat.parking.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.parking.dao.CarParkingDao;
import com.dat.parking.model.CarParking;

@Service("carParkingService")
public class CarParkingServiceImpl implements CarParkingService{

	@Autowired
	CarParkingDao carParkingDao;
	
	@Transactional
	@Override
	public void persistInformation(CarParking carParking) {
		// TODO Auto-generated method stub
		carParkingDao.persistInformation(carParking);
		
	}

}
