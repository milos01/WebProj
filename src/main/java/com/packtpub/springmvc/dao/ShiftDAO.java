package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;

public interface ShiftDAO {

	public List<Shift> shiftList();
	
	public void addNewShiftForStaff(Shift_schedule s);
	
	public void addNewRestShift(Shift sh);
	
}
