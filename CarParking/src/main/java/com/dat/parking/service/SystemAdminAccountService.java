package com.dat.parking.service;

import java.util.List;

import com.dat.parking.model.SystemAdminAccount;

public interface SystemAdminAccountService {
	void persistInformation(SystemAdminAccount systemAdminAccount);
	public List checkAccount(String name,String password);

}
