package com.dat.parking;

import java.io.Serializable;

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
    public CarParking carCtl=new CarParking();

    @ManagedProperty(value="#{carParkingService}")
    CarParkingService carParkingService;
    
    
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

	
	 public String getFloorName() {
		return floorName;
	}

	public void setFloorNo(String floorName) {
		this.floorName = floorName;
	}
//method CRUD
	
	public String persistInformation() {
		String bName=carCtl.getBuildingName();
         int count=Integer.parseInt(carCtl.getFloorName());
		for(int i=1;i<=count;i++) {

			carCtl.setFloorName("Floor"+Integer.toString(i));
			carCtl.setBuildingName(bName);
			System.out.println(carCtl.getFloorName());
			carParkingService.persistInformation(this.carCtl);

		}
		System.out.println("successful");

		return "index";
	}

	
}
