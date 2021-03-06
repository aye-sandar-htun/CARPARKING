package com.dat.parking;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.dat.parking.model.UserAdminAccount;
import com.dat.parking.service.UserAdminAccountService;

@ManagedBean
@SessionScoped
public class UserAdminAccountBean implements Serializable{
	@ManagedProperty(value="#{userAdminAccountService}")
	UserAdminAccountService userAdminAccountService;
	  public UserAdminAccount accountCtl=new UserAdminAccount();
	  
	 private String name;
	    private long contactNumber;
	    private String email;
	    private String address;
	    private String password;
	    private List<String> userRank=new LinkedList();
	    private String buildingName;
	   private String rank;
	   private List<String> floors=new LinkedList();
	   private List<String> slots=new LinkedList();
	   private List<UserAdminAccount> userAdminInformation;
	   
	   @PostConstruct
		public void init() {  
			userRank.add("Entry");
			userRank.add("Exit");
			
		}
	    
	

		public List<UserAdminAccount> getUserAdminInformation() {
		return userAdminInformation;
	}



	public void setUserAdminInformation(List<UserAdminAccount> userAdminInformation) {
		this.userAdminInformation = userAdminInformation;
	}



		public List<String> getUserRank() {
			return userRank;
		}
		public void setUserRank(List<String> userRank) {
			this.userRank = userRank;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(long contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
	    public UserAdminAccountService getUserAdminAccountService() {
			return userAdminAccountService;
		}
		public void setUserAdminAccountService(UserAdminAccountService userAdminAccountService) {
			this.userAdminAccountService = userAdminAccountService;
		}
		public UserAdminAccount getAccountCtl() {
			return accountCtl;
		}
		public void setAccountCtl(UserAdminAccount accountCtl) {
			this.accountCtl = accountCtl;
		}
		
		public String getBuildingName() {
			return buildingName;
		}
		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}
		
		public String getRank() {
			return rank;
		}
		public void setRank(String rank) {
			this.rank = rank;
		}
		
		
		public List<String> getFloors() {
			return floors;
		}

		public void setFloors(List<String> floors) {
			this.floors = floors;
		}

		public List<String> getSlots() {
			return slots;
		}

		public void setSlots(List<String> slots) {
			this.slots = slots;
		}

		//method CRUD
		public String persistInformation() {
		
			List l=userAdminAccountService.adminList(accountCtl.getName());
			if(l.isEmpty()) {
								
			userAdminAccountService.persistInformation(this.accountCtl);
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage("registerMsg", new FacesMessage("Successfully Registered.",""));
			 this.reset();
			return "userAdminLogin";
		}
			else {
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage("registerMsg", new FacesMessage("Exiting user.Try again!.",""));
				 this.reset();
				return "userAdminRegistration";
			}	
		}
		
		public String checkAccount() throws IOException {
			 List id=userAdminAccountService.checkAccount(accountCtl.getName(),accountCtl.getPassword());
			if(id.isEmpty()) {
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage("loginMsg", new FacesMessage("Wrong username or password.Try again!",""));
				 this.reset();
				 
				
				 return"";
			}
			else {
				
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage("loginMsg", new FacesMessage("Login Success",""));

			 userAdminInformation= getUserProfileInformation(accountCtl.getName());
			 if(rank.equals("Entry")) {
				 this.reset();
				
				 return "addCarParking.xhtml";
			 }
			 else {
				 this.reset();
				
				 return "exitUserCurrent";
			 }

			
			}
			
		}
	//choose rank
		public void onRankChange(){
			
			}
		
		//User Admin Profile
		public List<UserAdminAccount> getUserProfileInformation(String name){
			userAdminAccountService.getUserProfileInformation(name);
			return userAdminAccountService.getUserProfileInformation(name);
			
		}
		
		//reset
		public void reset() {
			
			this.accountCtl.setEmail("");
			this.accountCtl.setAddress("");
			this.accountCtl.setContactNumber(0);
			this.accountCtl.setPassword("");
		}
}
