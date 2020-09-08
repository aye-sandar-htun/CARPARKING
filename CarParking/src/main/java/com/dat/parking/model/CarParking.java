package com.dat.parking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parking_information")
public class CarParking {
private int building_id;
private String buildingName;
private String floorName;
private String slot;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="building_id")
public int getBuilding_id() {
	return building_id;
}

public void setBuilding_id(int building_id) {
	this.building_id = building_id;
}

@Column(name="building_name")
public String getBuildingName() {
	return buildingName;
}

public void setBuildingName(String buildingName) {
	this.buildingName = buildingName;
}

@Column(name="floor_name")
public String getFloorName() {
	return floorName;
}

public void setFloorName(String floorName) {
	this.floorName = floorName;
}

@Column(name="slot")
public String getSlot() {
	return slot;
}

public void setSlot(String slot) {
	this.slot = slot;
}

}
