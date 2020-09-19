package com.dat.parking.service;

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
	@Override
	public void persistInformation(CarParkingHistory carParkingHistory) {
		// TODO Auto-generated method stub
		carParkingHistoryDao.persistInformation(carParkingHistory);
	}
	@Transactional
	@Override
	public List<CarParkingHistory> carHistory() {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.carHistory();
	}
	@Transactional
	@Override
	public List dateList() {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.dateList();
	}
	@Transactional
	@Override
	public List<CarParkingHistory> searchByFloor(String floor) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.searchByFloor(floor);
	}
	@Transactional
	@Override
	public List<CarParkingHistory> searchByBuilding(String building) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.searchByBuilding(building);
	}
	@Transactional
	@Override
	public List<CarParkingHistory> searchByBuildingFloor(String building, String floor) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.searchByBuildingFloor(building, floor);
	}
	@Transactional
	@Override
	public List floorLists(String buildingName) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.floorLists(buildingName);
	}
	@Transactional
	@Override
	public List slotLists(String floorName, String buildingName) {
		// TODO Auto-generated method stub
		return carParkingHistoryDao.slotLists(floorName, buildingName);
	}

}
