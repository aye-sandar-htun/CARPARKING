package com.dat.parking;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;

import com.dat.parking.model.CarParkingHistory;
import com.dat.parking.service.CarParkingHistoryService;


@ManagedBean
@SessionScoped
public class CarParkingHistoryBean implements Serializable{
	 @ManagedProperty(value="#{carParkingHistoryService}")
	 CarParkingHistoryService carParkingHistoryService;
	  public CarParkingHistory historyCtl=new CarParkingHistory();
	  
	  
	private String carNumber;
	private String slot;
	private String floor;
	private String building;
	private Date date;
	private Time entryTime;
	private Time exitTime;
	private String submittedUser;
	
	private String selectedBuilding;
	private String selectedFloor;
	private Date selectedDate;
    private List dateList=new LinkedList();
	
	public CarParkingHistoryService getCarParkingHistoryService() {
		return carParkingHistoryService;
	}
	public void setCarParkingHistoryService(CarParkingHistoryService carParkingHistoryService) {
		this.carParkingHistoryService = carParkingHistoryService;
	}

	public CarParkingHistory getHistoryCtl() {
		return historyCtl;
	}
	public void setHistoryCtl(CarParkingHistory historyCtl) {
		this.historyCtl = historyCtl;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Time entryTime) {
		this.entryTime = entryTime;
	}
	public Time getExitTime() {
		return exitTime;
	}
	public void setExitTime(Time exitTime) {
		this.exitTime = exitTime;
	}
	public String getSubmittedUser() {
		return submittedUser;
	}
	public void setSubmittedUser(String submittedUser) {
		this.submittedUser = submittedUser;
	}
	
	
	public String getSelectedBuilding() {
		return selectedBuilding;
	}
	public void setSelectedBuilding(String selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}
	public String getSelectedFloor() {
		return selectedFloor;
	}
	public void setSelectedFloor(String selectedFloor) {
		this.selectedFloor = selectedFloor;
	}
	
	public List getDateList() {
		return dateList;
	}
	public void setDateList(List dateList) {
		this.dateList = dateList;
	}
	
	
	public Date getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
	//method CRUD
	public String persistInformation() {
		carParkingHistoryService.persistInformation(this.historyCtl);
		System.out.println("persist method success");
		
		return"index";
	}
	
	public List<CarParkingHistory> carHistory(){
		return this.carParkingHistoryService.carHistory();
	} 
	
	public void onBuildingChange() {  
		if(building!=null && !building.equals("")) { 
			selectedBuilding = building; 

		}
		else {
		selectedBuilding=null;
		}
	}
	public void onFloorChange() {  
		if(floor !=null && !floor.equals("")) { 
			selectedFloor= floor; 
		}  
		else {
			selectedFloor=null;
		}
	
	}
	public void onDateChange() {
		
			selectedDate=date;
			System.out.println("  selected date"+selectedDate);
		
	}
	public List dateList() {
		dateList=carParkingHistoryService.dateList();
		return dateList;
	}

	//search History
	 public List searchHistory() {
		 System.out.println("    History Search"+selectedBuilding+selectedFloor+selectedDate);
          if(selectedBuilding==null && selectedFloor==null && selectedDate==null) {
        	 return this.carParkingHistoryService.carHistory();
          }
          else if(selectedBuilding==null && selectedFloor!=null && selectedDate==null){
        	  return this.carParkingHistoryService.searchByFloor(selectedFloor);
          }
          else if(selectedBuilding!=null && selectedFloor==null && selectedDate==null){
        	  return this.carParkingHistoryService.searchByBuilding(selectedBuilding);
          }
          else if(selectedBuilding!=null && selectedFloor!=null && selectedDate==null) {
        	  return this.carParkingHistoryService.searchByBuildingFloor(building, floor);
          }
          else {
        	  return null;
          }
		 
	 }
	
}
