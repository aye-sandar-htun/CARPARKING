package com.dat.parking.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car_parking_history")
public class CarParkingHistory {
private int id;
private String carNumber;
private String slot;
private String floor;
private String building;
private Date date;
private Timestamp  entryTime;
private Timestamp  exitTime;
private String submittedUser;


@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

@Column(name="car_number")
public String getCarNumber() {
	return carNumber;
}

public void setCarNumber(String carNumber) {
	this.carNumber = carNumber;
}

@Column(name="slot")
public String getSlot() {
	return slot;
}

public void setSlot(String slot) {
	this.slot = slot;
}

@Column(name="floor")
public String getFloor() {
	return floor;
}

public void setFloor(String floor) {
	this.floor = floor;
}

@Column(name="building")
public String getBuilding() {
	return building;
}
public void setBuilding(String building) {
	this.building = building;
}

@Column(name="date")
public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

@Column(name="entry_time")
public Timestamp  getEntryTime() {
	return entryTime;
}

public void setEntryTime(Timestamp  entryTime) {
	this.entryTime = entryTime;
}

@Column(name="exit_time")
public Timestamp  getExitTime() {
	return exitTime;
}

public void setExitTime(Timestamp  exitTime) {
	this.exitTime = exitTime;
}

@Column(name="submited_user")
public String getSubmittedUser() {
	return submittedUser;
}
public void setSubmittedUser(String submittedUser) {
	this.submittedUser = submittedUser;
}



}
