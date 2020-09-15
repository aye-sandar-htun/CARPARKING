package com.dat.parking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_admin")
public class SystemAdminAccount {
private int id;
private String name;
private String email;
private String contactNumber;
private String password;

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

@Column(name="name")
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

@Column(name="email")
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@Column(name="contact_number")
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}


@Column(name="password")
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}



}
