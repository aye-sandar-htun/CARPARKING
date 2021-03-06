package com.dat.parking.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.dat.parking.model.CarParkingHistory;

public interface CarParkingHistoryDao {
      void persistInformation(CarParkingHistory carParkingHistory);
      public List<CarParkingHistory> carHistory();
      public List<CarParkingHistory> carHistoryForSelectedDate(Date date);
      public List dateList();
 	 public List floorLists(String buildingName);
	 public List slotLists(String floorName,String buildingName);
	 public List<CarParkingHistory> showCurrent(Date date);

     public List<CarParkingHistory> searchByCarNumber(Date date,String carNumber);
	 public List checkFreeSlot(String slot,String floorName,String buildingName,Timestamp exitTime);

	 public List checkExitingCar(String carNumber,Timestamp exitTime);
	 
	
	
	 


    void addExitTime(String buildingName,String floorName,String slot,Timestamp exitTime);

    
    //car history deleted
    public CarParkingHistory getById(int id);
    public void deleteParkingHistory(CarParkingHistory carhistorys);
}
