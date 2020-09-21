package com.dat.parking;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.transaction.Transactional;

import com.dat.parking.model.CarParkingHistory;
import com.dat.parking.model.UserAdminAccount;
import com.dat.parking.service.CarParkingHistoryService;


@ManagedBean
@SessionScoped
public class CarParkingHistoryBean implements Serializable{
	 @ManagedProperty(value="#{carParkingHistoryService}")
	 CarParkingHistoryService carParkingHistoryService;
	  public CarParkingHistory historyCtl=new CarParkingHistory();
	  UserAdminAccount accountCtl=new UserAdminAccount();
	  
	  
	private String carNumber;
	private String slot;
	private String floor;
	private String building;
	private Date date;
	private Timestamp  entryTime;
	private Timestamp  exitTime;
	private String submittedUser;
	
	private String selectedBuilding;
	private String selectedFloor;
	private String selectedSlot;
	private Date selectedDate;
    private List dateList=new LinkedList();
	private List floorList=new LinkedList();
	private List slotList=new LinkedList();
	
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
	public Timestamp  getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Timestamp  entryTime) {
		this.entryTime = entryTime;
	}
	public Timestamp  getExitTime() {
		return exitTime;
	}
	public void setExitTime(Timestamp  exitTime) {
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
	
	
	public List getFloorList() {
		return floorList;
	}
	public void setFloorList(List floorList) {
		this.floorList = floorList;
	}
	
	public List getSlotList() {
		return slotList;
	}
	public void setSlotList(List slotList) {
		this.slotList = slotList;
	}
	
	
	//method CRUD
	//add data to database 
	
	public UserAdminAccount getAccountCtl() {
		return accountCtl;
	}
	public void setAccountCtl(UserAdminAccount accountCtl) {
		this.accountCtl = accountCtl;
	}
	public String getSelectedSlot() {
		return selectedSlot;
	}
	public void setSelectedSlot(String selectedSlot) {
		this.selectedSlot = selectedSlot;
	}
	

	public String persistInformation(){
		
		List t=carParkingHistoryService.checkFreeSlot(historyCtl.getSlot(), historyCtl.getFloor(), historyCtl.getBuilding());
		if(t.isEmpty()) {
		
		System.out.println("    car Number  : "+historyCtl.getCarNumber());
		historyCtl.setFloor(selectedFloor);
		historyCtl.setSlot(selectedSlot);
		
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
        System.out.println(formatter.format(ts));                     
		  historyCtl.setEntryTime( ts);   
		   historyCtl.setDate(new Date());
		   System.out.println("    submitted user in car history "+accountCtl.getName());
		   historyCtl.setSubmittedUser(accountCtl.getName());
		   
		carParkingHistoryService.persistInformation(this.historyCtl);

		return"carHistory";
	}
		else {
			System.out.print("Exiting car in the slot");
			return "addCarParking";
		}
		}
	
	//Search history 
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
		selectedFloor=historyCtl.getFloor();
		
		if(selectedFloor!=null) { 
			 
		}  
		else {

			selectedFloor=null;
		}
	
	}
	
	
	
	public void onDateChange() {
		
			selectedDate=date;
			System.out.println("  selected Date "+selectedDate);
		
	}
	public List dateList() {
		dateList=carParkingHistoryService.dateList();
	
	    
		return dateList;
	}

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
	 
	 //floorlist in dropdown
	
	 public List floorLists() {
		 floorList=carParkingHistoryService.floorLists(historyCtl.getBuilding());
		return floorList;
		 
	 }
	 
	 public void onSelectedFloorChange() {
		 if(selectedFloor!=null) {
		 }
		 else {

		 }
	 }
	 
	public List slotLists() {

		slotList=carParkingHistoryService.slotLists(selectedFloor, historyCtl.getBuilding());
		return slotList;
		
	}
	public void selectedSlot(String slot) {
		selectedSlot=slot;
		persistInformation();
		   
		  
	}
	//show Current for entry user admin
	public List showCurrent(){
		Date date=new Date();
		return carParkingHistoryService.showCurrent(date);
		
	}
	
	
	
}
