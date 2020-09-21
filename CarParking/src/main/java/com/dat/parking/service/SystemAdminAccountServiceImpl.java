package com.dat.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dat.parking.dao.SystemAdminAccountDao;
import com.dat.parking.model.SystemAdminAccount;
@Service("systemAdminAccountService")
public class SystemAdminAccountServiceImpl implements SystemAdminAccountService{
	@Autowired
	SystemAdminAccountDao systemAdminAccountDao;
	
	@Transactional
	@Override
	public void persistInformation(SystemAdminAccount systemAdminAccount) {
		// TODO Auto-generated method stub
		systemAdminAccountDao.persistInformation(systemAdminAccount);

	}
	@Transactional
	@Override
	public List checkAccount(String name, String password) {
		// TODO Auto-generated method stub
		return systemAdminAccountDao.checkAccount(name, password);
	}
	
	
	@Transactional
	@Override
	public List adminList(String name) {
		return systemAdminAccountDao.adminList(name);
	}
	@Transactional
	@Override
	public List<SystemAdminAccount> getAdminProfileInformation(String name) {
		// TODO Auto-generated method stub
		return systemAdminAccountDao.getAdminProfileInformation(name);
	}
}
