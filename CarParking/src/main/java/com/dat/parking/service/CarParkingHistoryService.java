package com.dat.parking.service;

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


}
