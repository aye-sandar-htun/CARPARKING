package com.dat.parking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
    @ManagedProperty(value="#{carParkingService}")
    CarParkingService carParkingService;
    
//Getters and Setters
    public String getSlot() {
		return slot;
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

		
//method CRUD
	public String floorList() {
		floors=new LinkedList();
		int count=Integer.parseInt(carCtl.getFloorName());
		for(int i=1;i<=count;i++) {
			floors.add("floor"+i);}
		return "index";
	}
	String selectedFloor;
	
	public void onFloorChange() {  
		System.out.println("FloorChange");
		System.out.println("Selected floor:"+floorName);
		if(floorName !=null && !floorName.equals("")) { 
			selectedFloor = floorName; 
			 System.out.println("Selected floor:"+selectedFloor);
		}  
	}
	
	public String persistInformation() {
		String bName=carCtl.getBuildingName();
         int count=Integer.parseInt(carCtl.getFloorName());
		for(int i=1;i<=count;i++) {
			carCtl.setBuildingName(bName);
			carCtl.setFloorName("Floor"+Integer.toString(i));
			//carParkingService.persistInformation(this.carCtl);
			floors.add("floor"+i);
		}
		
		System.out.println("successful");
		System.out.println("Lists"+floors);

		return "index";
	}

	
	
	
}
