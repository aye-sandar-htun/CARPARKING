package com.dat.parking.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.parking.dao.CarParkingHistoryDao;
import com.dat.parking.model.CarParkingHistory;

@Service("carParkingHistoryService")
public class CarParkingHistoryServiceImpl implements CarParkingHistoryService{
	@Autowired
	CarParkingHistoryDao carParkingHistoryDao;
	
	@Transactional
	
	public void persistInformation(CarParkingHistory carParkingHistory) {
		// TODO Auto-generated method stub
		carParkingHistoryDao.persistInformation(carParkingHistory);
	}
	@Transactional
	
	public List<CarParkingHistory> carHistory() {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.carHistory();
	}
	@Transactional
	
	public List dateList() {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.dateList();
	}
	
	@Transactional
	
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.floorLists(buildingName);
	}
	@Transactional
	
	public List slotLists(String floorName, String buildingName) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.slotLists(floorName, buildingName);
	}
	@Transactional
	
	public List<CarParkingHistory> showCurrent(Date date) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.showCurrent(date);
	}
	@Transactional
	
	public List<CarParkingHistory> searchByCarNumber(Date date,String carNumber) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.searchByCarNumber(date,carNumber);
	}

	
	
	@Transactional
	
	 public List checkFreeSlot(String slot,String floorName,String buildingName,Timestamp exitTime) {
		return carParkingHistoryDao.checkFreeSlot(slot,floorName,buildingName,exitTime);
	}
	
	@Transactional
	 public List checkExitingCar(String carNumber,Timestamp exitTime) {
		return carParkingHistoryDao.checkExitingCar(carNumber, exitTime);
	}
	 

	@Transactional
	
	public void addExitTime(String building, String floor, String slot, Timestamp exitTime) {
		// TODO Auto-generated method stub
		carParkingHistoryDao.addExitTime(building, floor, slot, exitTime);
	}
	@Transactional
	@Override
	public List<CarParkingHistory> carHistoryForSelectedDate(Date date) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.carHistoryForSelectedDate(date);
	}
	
	
	//carParking history deleted
	@Transactional
	  public CarParkingHistory getById(int id) {
		  return carParkingHistoryDao.getById(id);
	  }
	
	@Transactional
	public void deleteParkingHistory(CarParkingHistory carhistorys) {
		carParkingHistoryDao.deleteParkingHistory(carhistorys);
	}

}
