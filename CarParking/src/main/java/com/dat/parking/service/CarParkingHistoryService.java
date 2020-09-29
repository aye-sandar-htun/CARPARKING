package com.dat.parking.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dat.parking.model.CarParkingHistory;

public interface CarParkingHistoryService {
    void persistInformation(CarParkingHistory carParkingHistory);
    public List<CarParkingHistory> carHistory();
    public List dateList();
    public List<CarParkingHistory> searchByFloor(String floor);
    public List<CarParkingHistory> searchByBuilding(String building);
    public List<CarParkingHistory> searchByBuildingFloor(String building,String floor);
	 public List floorLists(String buildingName);
	 public List slotLists(String floorName,String buildingName);
	 public List<CarParkingHistory> showCurrent(Date date);
     public List<CarParkingHistory> searchByCarNumber(Date date,String carNumber);


	 public List checkFreeSlot(String slot,String floorName,String buildingName,Timestamp exitTime);

	 public List checkExitingCar(String carNumber,Timestamp exitTime);

	 
	
	  CarParkingHistory findById(int id);
	   void deleteCarHistory(CarParkingHistory cars);
	 

	    void addExitTime(String building,String floor,String slot,Timestamp exitTime);

}
