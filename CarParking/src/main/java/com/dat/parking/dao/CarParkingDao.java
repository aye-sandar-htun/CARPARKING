package com.dat.parking.dao;

import java.util.List;
import java.util.Set;

import com.dat.parking.model.CarParking;

public interface CarParkingDao {
	 void persistInformation(CarParking carParking);
	 public List buildingLists();
	 public List floorLists(String buildingName);
	 public List slotLists(String floorName,String buildingName);
	 void deleteBuilding(String buildingName);
	 void deleteFloor(String buildingName,String floorName);
	 void deleteSlot(String buildingName,String floorName,String slot);
    
	 }
