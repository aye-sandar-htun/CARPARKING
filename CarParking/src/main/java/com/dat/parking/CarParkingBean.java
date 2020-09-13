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

import com.dat.parking.model.CarParking;
import com.dat.parking.service.CarParkingService;

@ManagedBean
@SessionScoped
public class CarParkingBean implements Serializable{
	public String buildingName;
	public String floorName;
	public String slot;
	
    public CarParking carCtl=new CarParking();
    private List<String> floors;
    private List<String>floor;
    private String selectedBuilding;
    private String selectedFloor;
    private String selectedSlot;
    private List<String> buildings;
    @ManagedProperty(value="#{carParkingService}")
    CarParkingService carParkingService;
    
    
//Getters and Setters
    
    public String getSlot() {
		return slot;
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

	public String getSelectedSlot() {
		return selectedSlot;
	}

	public void setSelectedSlot(String selectedSlot) {
		this.selectedSlot = selectedSlot;
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

    
    public List<String> getFloors() {
		return floors;
	}

	
	public void setFloors(List<String> floors) {
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
	public String floorList() {
		
		int count=Integer.parseInt(carCtl.getFloorName());
		if(count>7) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("More than 7floors are not allowed!"));
		}
		else {
		for(int i=1;i<=count;i++) {
			floors.add("Floor"+i);}
		}
		return "index";
	}
	
	
	public void onFloorChange() {  
		if(floorName !=null && !floorName.equals("")) { 
			selectedFloor= floorName; 
		}  
	
	}
	
	
	//method CRUD
	public String persistInformation() {
		String bName=carCtl.getBuildingName();
		
		  int slotcount=Integer.parseInt(carCtl.getSlot());
		  if(selectedFloor==null || selectedFloor.equals("")) {
			  FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Please select a floor."));
		  }
		  else {
		  for(int i=1;i<=slotcount;i++) {
			  carCtl.setBuildingName(bName);
		      carCtl.setFloorName(selectedFloor);
		      carCtl.setSlot("Slot"+i);
		      carParkingService.persistInformation(this.carCtl); 
		      FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Successfully added for "+selectedFloor+"."));
		      }
		  }

		return "index";
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
		List slot=carParkingService.slotLists(floorName,buildingName);
		 return slot;
	 }
	 
	//get buiding name to delete
		public void onBuildingChange() {  
			if(buildingName !=null && !buildingName.equals("")) { 
				selectedBuilding = buildingName; 
				 floorLists(selectedBuilding);

			}
		
		}
//delete building
	 public void deleteBuilding(String buildingName) {
		 carParkingService.deleteBuilding(selectedBuilding);
		  FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Successfully deleted."));
		
	 }
//delete floor
	 public void deleteFloor(String buildingName,String floorName) {
     
		 carParkingService.deleteFloor(selectedBuilding, selectedFloor);
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Successfully deleted."));
		
		 System.out.println("deleted successfully");
	 }
}
