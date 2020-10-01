package com.dat.parking.service;

import java.util.List;
import java.util.Set;

import com.dat.parking.model.CarParking;

public interface CarParkingService {
	 void persistInformation(CarParking carParking);
	 public List buildingLists();
	 public List floorLists(String buildingName);
	 public List slotLists(String floorName,String buildingName);
	 void deleteBuilding(String buildingName);
	 void deleteFloor(String buildingName,String floorName);
	 void deleteSlot(String buildingName,String floorName,String slot);
	 
	 public List buildingList(String bName);
	 public void updateStatus(String building,String floor,String slot);
	 void updateStatusAvailable(String building,String floor,String slot);
	 public String getStatus(String building,String floor,String slot);
	 
     public List buildList(String buildingName);


     public List statusBuildingList(String buildingName);
     public List statusFloorList(String buildingName,String floorName);
     public List statusSlotList(String buildingName,String floorName,String slot);
     public List getAvailableSlot(String buildingName,String floorName);
     void disableSlot(String buildingName,String floorName,String slot);
     public List getDisableSlot(String buildingName,String floorName);

}
