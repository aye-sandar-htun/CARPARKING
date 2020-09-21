package com.dat.parking.service;

import java.util.List;

import com.dat.parking.model.UserAdminAccount;

public interface UserAdminAccountService {
    void persistInformation(UserAdminAccount userAdminAccount);
	public List checkAccount(String name,String password);
<<<<<<< HEAD
	public List<UserAdminAccount> getUserProfileInformation(String name);

=======
	
	public List adminList(String name);
>>>>>>> branch 'master' of https://github.com/aye-sandar-htun/CARPARKING.git
}
