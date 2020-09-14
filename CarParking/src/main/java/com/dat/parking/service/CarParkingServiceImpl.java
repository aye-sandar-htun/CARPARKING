package com.dat.parking.service;

import java.util.List;
import java.util.Set;

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
	
	@Transactional
	@Override
	public List buildingLists() {
		// TODO Auto-generated method stub
		return carParkingDao.buildingLists();
	}
	@Transactional
	@Override
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		return carParkingDao.floorLists(buildingName);
	}
	@Transactional
	@Override
	public List slotLists(String floorName,String buildingName) {
		// TODO Auto-generated method stub
		return carParkingDao.slotLists(floorName,buildingName);
	}
	@Transactional
	@Override
	public void deleteBuilding(String buildingName) {
		// TODO Auto-generated method stub
		carParkingDao.deleteBuilding(buildingName);
	}
	@Transactional
	@Override
	public void deleteFloor(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		carParkingDao.deleteFloor(buildingName, floorName);
		
	}
	@Transactional
	@Override
	public void deleteSlot(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		carParkingDao.deleteSlot(buildingName, floorName, slot);
	}



	
}
