package com.dat.parking.dao;

import java.util.Date;
import java.util.List;

import com.dat.parking.model.CarParkingHistory;

public interface CarParkingHistoryDao {
      void persistInformation(CarParkingHistory carParkingHistory);
      public List<CarParkingHistory> carHistory();
      public List dateList();
      public List<CarParkingHistory> searchByFloor(String floor);
      public List<CarParkingHistory> searchByBuilding(String building);
      public List<CarParkingHistory> searchByBuildingFloor(String building,String floor);
 	 public List floorLists(String buildingName);
	 public List slotLists(String floorName,String buildingName);
	 public List<CarParkingHistory> showCurrent(Date date);
<<<<<<< HEAD
     public List<CarParkingHistory> searchByCarNumber(Date date,String carNumber);
=======

	 
	 public List checkFreeSlot(String slot,String floorName,String buildingName);
>>>>>>> branch 'master' of https://github.com/aye-sandar-htun/CARPARKING.git

}
