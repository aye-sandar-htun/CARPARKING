package com.dat.parking;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.transaction.Transactional;

import org.primefaces.event.SelectEvent;

import com.dat.parking.model.CarParkingHistory;
import com.dat.parking.model.UserAdminAccount;
import com.dat.parking.service.CarParkingHistoryService;
import com.dat.parking.service.CarParkingService;


@ManagedBean
@SessionScoped
public class CarParkingHistoryBean implements Serializable{
	 @ManagedProperty(value="#{carParkingHistoryService}")
	 CarParkingHistoryService carParkingHistoryService;
	 @ManagedProperty(value="#{carParkingService}")

	 CarParkingService carParkingService;
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
	private List<CarParkingHistory> historylist;
	private List<CarParkingHistory> filteredRecords;
	private List<CarParkingHistory> showCurrentList;
	private List<CarParkingHistory> showFilteredCurrentList;
    private String status;

	
	public CarParkingService getCarParkingService() {
	return carParkingService;
}
public void setCarParkingService(CarParkingService carParkingService) {
	this.carParkingService = carParkingService;
}
	public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
	public List<CarParkingHistory> getShowCurrentList() {
		return showCurrentList;
	}
	public void setShowCurrentList(List<CarParkingHistory> showCurrentList) {
		this.showCurrentList = showCurrentList;
	}
	public List<CarParkingHistory> getShowFilteredCurrentList() {
		return showFilteredCurrentList;
	}
	public void setShowFilteredCurrentList(List<CarParkingHistory> showFilteredCurrentList) {
		this.showFilteredCurrentList = showFilteredCurrentList;
	}
	public List<CarParkingHistory> getHistorylist() {
		return historylist;
	}
	public void setHistorylist(List<CarParkingHistory> historylist) {
		this.historylist = historylist;
	}
	public List<CarParkingHistory> getFilteredRecords() {
		return filteredRecords;
	}
	public void setFilteredRecords(List<CarParkingHistory> filteredRecords) {
		this.filteredRecords = filteredRecords;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
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
		historyCtl.setFloor(selectedFloor);
		historyCtl.setSlot(selectedSlot);
		//List t=carParkingHistoryService.checkFreeSlot(historyCtl.getSlot(), historyCtl.getFloor(), historyCtl.getBuilding(),historyCtl.getExitTime());
		List t=carParkingHistoryService.checkExitingCar(historyCtl.getCarNumber(), historyCtl.getExitTime());
		
		if(t.isEmpty()) {
		
		String status=carParkingService.getStatus(historyCtl.getBuilding(), historyCtl.getFloor(), historyCtl.getSlot());
		System.out.println(" status  "+status);
		if(status.equals("available")){
		
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("msgs", new FacesMessage("Successfully Parked."));
			
			updateStatus();
	
		
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
		  historyCtl.setEntryTime(ts);   
		   historyCtl.setDate(new Date());
		   historyCtl.setSubmittedUser(submittedUser);
		   
		carParkingHistoryService.persistInformation(this.historyCtl);
	   
		return"carHistory";
			  }
		else {
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_WARN,"Exiting car in the slot","Exiting car in the slot"));
			
			return "addCarParking";
				}
		}
		else {
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_WARN,"Exiting car in the slot","Exiting car in the slot"));
			
			return "addCarParking";
		}
	}
	
	//show car history 
  
    public List historylist() {
    	
        	if(selectedDate==null) {
    		 historylist = carParkingHistoryService.carHistory();
    		 return historylist;
        	}
        	else {
        		System.out.println(" list for selected ate");
        		historylist=carParkingHistoryService.carHistoryForSelectedDate(selectedDate);
        		 return historylist;
        	}
    	
      //  historylist = carParkingHistoryService.carHistory(selectedDate);
       // return historylist;
    	//}

    }
    @PostConstruct
    public List showCurrentList() {
        showCurrentList=carParkingHistoryService.showCurrent(today);
return showCurrentList;
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

		
			/*
			 * if(selectedFloor!=null && !selectedFloor.equals("")) {
			 * selectedFloor=historyCtl.getFloor();
			 * 
			 * } else {
			 * 
			 * selectedFloor=null; }
			 */
	
	}
	
	
	
	public void onDateChange() {
		
			selectedDate=date;
		
	}
	public List dateList() {
		dateList=carParkingHistoryService.dateList();
	
	    
		return dateList;
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
	public void selectedSlot(String name,String slot) {
		submittedUser=name;
		selectedSlot=slot;
		persistInformation();
		   
		  
	}
	//show Current for entry user admin
	Date today=new Date();
	public List showCurrent(){
		
		return carParkingHistoryService.showCurrent(today);
		
	}

	

public void updateStatus() {
	 carParkingService.updateStatus(historyCtl.getBuilding(), selectedFloor, selectedSlot);
	 
}
//clear slot
public void clearSlot(String f,String s) {
	String status=carParkingService.getStatus(historyCtl.getBuilding(), f, s);
	if(status.equals("available")) {
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"Already exiting!",""));
	}
	else {
	Date date = new Date();  
    Timestamp ts=new Timestamp(date.getTime());  
    carParkingHistoryService.addExitTime(historyCtl.getBuilding(), f, s, ts);
    carParkingService.updateStatusAvailable(historyCtl.getBuilding(), f, s);
    FacesContext context = FacesContext.getCurrentInstance();
	 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"Car Exit",""));
}
}
public String toggleStatus(String f,String s) {

	String status=carParkingService.getStatus(historyCtl.getBuilding(), f, s);
	System.out.println(" status  "+status);
	if(status.equals("available")){
		return "green";
	}

	else if(status.equals("occupied")){
	return "red";}
else {
	return "gray";
}
}




  //SystemAdmin deletecarHistory 
	public void deleteCarHistory(int id) {
  CarParkingHistory cars=carParkingHistoryService.findById(id);
  getCarParkingHistoryService().deleteCarHistory(cars); 
  FacesContext context=FacesContext.getCurrentInstance();
  context.addMessage(null, new FacesMessage("Delete successfully"));
  
  }
 
//date from calendar
	public void dateChange(SelectEvent selectEvent) {
		 Date date =selectedDate;
   
	}
	//compute duration
	public String computeDuration(Timestamp entryTime,Timestamp exitTime) {
		String duration;
		 
		if(exitTime!=null) {
			
				
				    long millis =exitTime.getTime()-entryTime.getTime();
				    int sec=(int)millis/1000;
				    int hr=sec/3600;
				    int min=(sec%3600)/60;
				    sec=(sec%3600)%60;
				     duration=hr+":"+min+":"+sec;
				    
				    return duration;
		}
		else {
		return"";
		}
		
	}
	LocalDate currentdate = LocalDate.now();
	 Month currentMonth = currentdate.getMonth();
	//show slot detail
	 public String showMessage(String building,String floor,String slot) {
			String status=carParkingService.getStatus(building, floor, slot);

		 String message="Hi";
		 return message;
	 }
}
