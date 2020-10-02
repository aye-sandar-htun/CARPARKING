package com.dat.parking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import com.dat.parking.model.CarParking;
import com.dat.parking.service.CarParkingService;

@ManagedBean
@SessionScoped
public class CarParkingBean implements Serializable{
	public String buildingName;
	public String floorName;
	public String slot;
	
    public CarParking carCtl=new CarParking();
    private Set<String> floors=new LinkedHashSet();
    private List<String>floor=new LinkedList();
    private String selectedBuilding;
    private String selectedFloor;
    private List selectedSlots=new LinkedList();
    private List<String> buildings;
    private String status;
    private String selectedBuildingForDisable;
    private String selectedFloorForDisable;
    @ManagedProperty(value="#{carParkingService}")
    CarParkingService carParkingService;
    
    
//Getters and Setters
    
    public String getSlot() {
		return slot;
	}

	public String getSelectedFloorForDisable() {
		return selectedFloorForDisable;
	}

	public void setSelectedFloorForDisable(String selectedFloorForDisable) {
		this.selectedFloorForDisable = selectedFloorForDisable;
	}

	public String getSelectedBuildingForDisable() {
		return selectedBuildingForDisable;
	}

	public void setSelectedBuildingForDisable(String selectedBuildingForDisable) {
		this.selectedBuildingForDisable = selectedBuildingForDisable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSelectedslot() {
		return selectedslot;
	}

	public void setSelectedslot(String selectedslot) {
		this.selectedslot = selectedslot;
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


	public List getSelectedSlots() {
		return selectedSlots;
	}

	public void setSelectedSlots(List selectedSlots) {
		this.selectedSlots = selectedSlots;
	}

	public List<String> getFloor() {
		return floor;
	}

	public void setFloor(List<String> floor) {
		this.floor = floor;
	}

	public List<String> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<String> buildings) {
		this.buildings = buildings;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

    
   
	

   public Set<String> getFloors() {
		return floors;
	}

	public void setFloors(Set<String> floors) {
		this.floors = floors;
	}

public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

public CarParkingService getCarParkingService() {
		return carParkingService;
	}

	public void setCarParkingService(CarParkingService carParkingService) {
		this.carParkingService = carParkingService;
	}

  public CarParking getCarCtl() {
		return carCtl;
	}

	public void setCarCtl(CarParking carCtl) {
		this.carCtl = carCtl;
	}

   public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

		
//add floorlist to dropdown
	int count;
	public String floorList() {
		List l=carParkingService.buildList(carCtl.getBuildingName());
		if(l.isEmpty()) {
		
		try {
		 count=Integer.parseInt(carCtl.getFloorName());
		}
		catch(NumberFormatException e){
			FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"Number of floor should be integer!","Number of floor should be integer!"));
		}
		if(count>7) {
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"More than 7floors are not allowed!","More than 7floors are not allowed!"));
		 
			
		}
		else {
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage(count+"floors are added for building "+carCtl.getBuildingName()));
		for(int i=1;i<=count;i++) {
			floors.add("Floor"+i);}
		}
		return "index";
	}
	
	else {
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage("msgs", new FacesMessage("Exiting name of building."));
	  return "addParkingSlot";
	}
	}
	

	
	
	//method CRUD
	public String persistInformation() {
		
		
		String bName=carCtl.getBuildingName();
		
		  int slotcount=Integer.parseInt(carCtl.getSlot());
		  if(slotcount>100) {
			  FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"More than 100slots are not allowed!","More than 100slots are not allowed!"));
		  }
		  else {
		  if(selectedFloor==null || selectedFloor.equals("")) {
			
			  FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO,"Please select a floor.","Please select a floor."));
			  
			  
		  }
		  else {
		  for(int i=1;i<=slotcount;i++) {
			  carCtl.setBuildingName(bName);
		      carCtl.setFloorName(selectedFloor);
		      carCtl.setSlot("Slot"+i);
		      carCtl.setStatus("available");
		      carParkingService.persistInformation(this.carCtl); 

		     floors.remove(selectedFloor);

		      this.reset();
		      //TabView tabView = FacesContext.getCurrentInstance().getViewRoot().findComponent(view); 


		      }
		  
		  FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Successfully added for "+selectedFloor+"."));
		  
		  }
		
		  }
		  
		return "addParkingSlot";
	}

	//building view
	
	 public List buildingLists(){
		 buildings=carParkingService.buildingLists();
		 for(int i=0;i<buildings.size();i++) {
			 floorLists((String) buildings.get(i));
		 }
		return buildings;
		 
	 }
	
	
	 //floor view
	 public List floorLists(String buildingName) {
			  floor=carParkingService.floorLists(buildingName); 
			  for(int i=0;i<floor.size();i++) { 
				  slotLists((String)floor.get(i),buildingName);
				  
			  }
			 
					 return floor;
	 }
	 //slot view
	 public List slotLists(String floorName,String buildingName) {
		
		 selectedSlots=carParkingService.slotLists(floorName,buildingName);
		 return selectedSlots;
	 }
	 
	//get buidingName,floorName and Slot to delete
		public void onBuildingChange() {  
			if(buildingName !=null && !buildingName.equals("")) { 
				selectedBuilding = buildingName; 
				 selectedBuildingForDisable=buildingName;
				 floorLists(selectedBuilding);

			}
		
		}
		public void onFloorChange() {  
			if(floorName !=null && !floorName.equals("")) { 
				selectedFloor= floorName; 
				selectedFloorForDisable=floorName;
				slotLists(selectedFloor,selectedBuilding);
			}  
		
		}
		String selectedslot;
		public void onSlotChange() {
			if(slot!=null && !slot.equals("")) {
				selectedslot=slot;
			}
		}
//delete building
	 public void deleteBuilding(String buildingName) {
		 List t=carParkingService.statusBuildingList(selectedBuilding);
		 
		 
		 if(t.isEmpty()) {
		 
		 carParkingService.deleteBuilding(selectedBuilding);
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Successfully deleted.",""));
	
		
	 }
		 else {
			 FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Please attention.","car exiting in a building so u can not deleted."));
			
			 }
		 }
//delete floor
	 public void deleteFloor(String buildingName,String floorName) {
		 List f=carParkingService.statusFloorList(selectedBuilding, selectedFloor);
		 if(f.isEmpty()) {
     
		 carParkingService.deleteFloor(selectedBuilding, selectedFloor);
		
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Successfully deleted.",""));
	
			 }
		 else {
			 	FacesContext context = FacesContext.getCurrentInstance();
			 	context.addMessage(null, new FacesMessage("Please attention ","car exiting in a floor so u can not deleted.."));
			
		 }
		 
	 }
	 //delete slot
	 public void deleteSlot(String slot) {
			
		 List s=carParkingService.statusSlotList(selectedBuilding, selectedFloor, selectedslot);
		 if(s.isEmpty()) {
			 System.out.println("Building Name is "+selectedBuilding+" Floor Name is "+selectedFloor+"slot is "+slot);
		 carParkingService.deleteSlot(selectedBuilding, selectedFloor, slot);
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Successfully deleted.",""));
		
		 }
		 else {
			
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Please attention","car exiting in a slot so u can not deleted."));
			
		 }
		 
	 
	 }
	 
	 //reset
	 public void reset() {
		 this.carCtl.setSlot("");
	 }
	 
	 public void resets() {
		 this.carCtl.setBuildingName("");
		 this.carCtl.setFloorName("");
	 }

	
	//button color
	 public String toggleStatus(String b,String f,String s) {

			String status=carParkingService.getStatus(b, f, s);
			if(status.equals("available")){
				return "green";
			}
			else if(status.equals("occupied")){
			return "red";
			}
			else {
				return "gray";
			}
		}
	 //get available slot to do disable
	
	 public List getAvailableSlot() {
		List availableSlot=carParkingService.getAvailableSlot(selectedBuildingForDisable,selectedFloorForDisable);
		
		 return availableSlot; 
		 
		
	 }
	 //disable selected slot
	 public void disableSlot(String slot) {

		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage(selectedBuildingForDisable+">"+selectedFloorForDisable+">"+slot+" is temporarily out of service now!"));
		 carParkingService.disableSlot(selectedBuildingForDisable, selectedFloorForDisable, slot);
	 }
	 //enable selected slot
	 public List getDisableSlot() {
		return carParkingService.getDisableSlot(selectedBuildingForDisable,selectedFloorForDisable);
	 }
	 public void enableSlot(String slot) {
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage(selectedBuildingForDisable+">"+selectedFloorForDisable+">"+slot+" is availablen now!"));
		 carParkingService.updateStatusAvailable(selectedBuildingForDisable, selectedFloorForDisable, slot);
	 }
}
