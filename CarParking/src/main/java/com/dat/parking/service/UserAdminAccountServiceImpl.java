package com.dat.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.parking.dao.UserAdminAccountDao;
import com.dat.parking.model.UserAdminAccount;
@Service("userAdminAccountService")
public class UserAdminAccountServiceImpl implements UserAdminAccountService{
	@Autowired
	UserAdminAccountDao userAdminAccountDao;
	
	@Transactional
	
	public void persistInformation(UserAdminAccount userAdminAccount) {
		// TODO Auto-generated method stub
		userAdminAccountDao.persistInformation(userAdminAccount);
	}
	@Transactional
	
	public List checkAccount(String name, String password) {
		// TODO Auto-generated method stub
		return userAdminAccountDao.checkAccount(name, password);
	}
	@Transactional
	
	public List<UserAdminAccount> getUserProfileInformation(String name) {
		// TODO Auto-generated method stub
		return userAdminAccountDao.getUserProfileInformation(name);
	}
	
	@Transactional
	
	public List adminList(String name) {
		return userAdminAccountDao.adminList(name);
	}
	
}
