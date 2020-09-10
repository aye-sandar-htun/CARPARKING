package com.dat.parking.service;

import java.util.List;
import java.util.Set;

import com.dat.parking.model.CarParking;

public interface CarParkingService {
	 void persistInformation(CarParking carParking);
	 public List buildingList();
	 public List floorLists(String buildingName);

}
