package com.dat.parking;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TabChangeEvent;

import com.dat.parking.model.SystemAdminAccount;
import com.dat.parking.model.UserAdminAccount;
import com.dat.parking.service.SystemAdminAccountService;

@ManagedBean
@SessionScoped
public class SystemAdminAccountBean implements Serializable{
	  @ManagedProperty(value="#{systemAdminAccountService}")
	  SystemAdminAccountService systemAdminAccountService;
	  public SystemAdminAccount accountCtl=new SystemAdminAccount();
	  
	  private String name;
	  private String email;
	  private String contactNumber;
	  private String password;
	  private List<SystemAdminAccount> adminInformation;
	  //getters and setters
	  
	  
	public SystemAdminAccountService getSystemAdminAccountService() {
		return systemAdminAccountService;
	}
	
	public List<SystemAdminAccount> getAdminInformation() {
		return adminInformation;
	}

	public void setAdminInformation(List<SystemAdminAccount> adminInformation) {
		this.adminInformation = adminInformation;
	}

	public SystemAdminAccount getAccountCtl() {
		return accountCtl;
	}
	public void setAccountCtl(SystemAdminAccount accountCtl) {
		this.accountCtl = accountCtl;
	}
	public void setSystemAdminAccountService(SystemAdminAccountService systemAdminAccountService) {
		this.systemAdminAccountService = systemAdminAccountService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	  
	//method CRUD
	public String persistInformation() {
		List l=systemAdminAccountService.adminList(accountCtl.getName());
		if(l.isEmpty()) {
		System.out.println("Password "+accountCtl.getPassword());
				
		systemAdminAccountService.persistInformation(this.accountCtl);
		System.out.println("persist method success");

		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Successfully Registered!"));
<<<<<<< HEAD
		return"index";
=======
		
>>>>>>> branch 'master' of https://github.com/aye-sandar-htun/CARPARKING.git

		
<<<<<<< HEAD
=======
		return"systemAdminLogin";

>>>>>>> branch 'master' of https://github.com/aye-sandar-htun/CARPARKING.git
	}
		else {
			
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Exiting User name Change and Try again!"));
			 System.out.print("Exiting user");
			return "systemAdminRegistration";
		}
		}
	
	//SystemAdminLogin
	public String checkAccount() {
		 List id=systemAdminAccountService.checkAccount(accountCtl.getName(),accountCtl.getPassword());
		if(id.isEmpty()) {
			System.out.println("Login fail");
			 FacesContext context = FacesContext.getCurrentInstance();
			 context.addMessage(null, new FacesMessage("Wrong username or password.Try again!"));
				return "systemAdminLogin";

		}
		else {
		System.out.println("Login success");
		 FacesContext context = FacesContext.getCurrentInstance();
		 context.addMessage(null, new FacesMessage("Login success."));
		 adminInformation= getAdminProfileInformation(accountCtl.getName());
		return "tapview";
		}
		
		
	}
	//System Admin Profile
	public List<SystemAdminAccount> getAdminProfileInformation(String name){
		return systemAdminAccountService.getAdminProfileInformation(name);
	}
	
	
	
	
}
