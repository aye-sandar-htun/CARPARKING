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
	    private String contactNumber;
	    private String email;
	    private String address;
	    private String password;
	    private List<String> userRank=new LinkedList();
	    private String buildingName;
	   private String rank;
	   private List<String> floors=new LinkedList();
	   private List<String> slots=new LinkedList();
	   
	   
	   @PostConstruct
		public void init() {  
			userRank.add("Entry");
			userRank.add("Exit");
			System.out.println("         Rank "+userRank);
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
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
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
			System.out.println("Password "+accountCtl.getPassword());					
			userAdminAccountService.persistInformation(this.accountCtl);
			System.out.println("persist method success");
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Successfully Registered!"));
			return "userAdminLogin";
		}
			else {
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage(null, new FacesMessage("Exiting User name Change and Try again!"));
				 System.out.print("Exiting user");
				return "userAdminRegistration";
			}	
		}
		
		public String checkAccount() throws IOException {
			System.out.println(" account cl name "+accountCtl.getName());
			 List id=userAdminAccountService.checkAccount(accountCtl.getName(),accountCtl.getPassword());
			if(id.isEmpty()) {
				System.out.println("Login fail");
				 FacesContext context = FacesContext.getCurrentInstance();
				 context.addMessage(null, new FacesMessage("Wrong username or password.Try again!"));

				 return "userAdminLogin";
			}
			else {
			System.out.println("Login success");
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Login success."));
			// String url = "userAdminHomePage"; // Your URL here
			// FacesContext context1 = FacesContext.getCurrentInstance();

			// context1.getExternalContext().redirect(url);
			 if(rank.equals("Entry")) {
				 return "addCarParking";
			 }
			 else {
				 return "view";
			 }
			
			}
			
		}
	//choose rank
		public void onRankChange(){
			if(rank!=null && rank.equals("")) {
				
				System.out.println("   Selected Rank "+rank);

		}
			else {
				System.out.println("   Selected Rank "+rank);

			}
			}
		
		
}
