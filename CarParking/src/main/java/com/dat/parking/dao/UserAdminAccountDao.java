package com.dat.parking.dao;

import java.util.List;

import com.dat.parking.model.UserAdminAccount;

public interface UserAdminAccountDao {
    void persistInformation(UserAdminAccount userAdminAccount);
	public List checkAccount(String name,String password);
	
	public List adminList(String name);
}
