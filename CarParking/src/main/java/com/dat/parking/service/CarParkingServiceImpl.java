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
	
	public void persistInformation(CarParking carParking) {
		// TODO Auto-generated method stub
		carParkingDao.persistInformation(carParking);
		
	}
	
	@Transactional
	
	public List buildingLists() {
		// TODO Auto-generated method stub
		return carParkingDao.buildingLists();
	}
	@Transactional
	
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		return carParkingDao.floorLists(buildingName);
	}
	@Transactional
	
	public List slotLists(String floorName,String buildingName) {
		// TODO Auto-generated method stub
		return carParkingDao.slotLists(floorName,buildingName);
	}
	@Transactional
	
	public void deleteBuilding(String buildingName) {
		// TODO Auto-generated method stub
		carParkingDao.deleteBuilding(buildingName);
	}
	@Transactional
	
	public void deleteFloor(String buildingName, String floorName) {
		// TODO Auto-generated method stub
		carParkingDao.deleteFloor(buildingName, floorName);
		
	}
	@Transactional
	
	public void deleteSlot(String buildingName, String floorName, String slot) {
		// TODO Auto-generated method stub
		carParkingDao.deleteSlot(buildingName, floorName, slot);
	}



	 public List buildingList(String bName) {
		 return this.carParkingDao.buildingList(bName);
	 }
	 @Transactional
	
	public void updateStatus(String building, String floor, String slot) {
		// TODO Auto-generated method stub
		 carParkingDao.updateStatus(building, floor, slot);
	}
	 @Transactional
	
	public void updateStatusAvailable(String building, String floor, String slot) {
		// TODO Auto-generated method stub
		 carParkingDao.updateStatusAvailable(building, floor, slot);
	}
	 @Transactional
	
	public String getStatus(String building, String floor, String slot) {
		// TODO Auto-generated method stub
		return carParkingDao.getStatus(building, floor, slot);
	}
	
}
