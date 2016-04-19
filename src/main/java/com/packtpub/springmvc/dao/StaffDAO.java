package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Staff;

public interface StaffDAO {
	
	public void addStaff(Staff s);
	
	public void updateStaff(Staff a);

	public List<Staff> listStaffs();
	
	public Staff getStaff(String email);
	
	public void removeStaff(int id);
	
}
