package com.packtpub.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packtpub.springmvc.model.CalendarJSONShow;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class JSONController {
	
	private PersonService personService;
	
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps){
		this.personService=ps;
	}
	
	@RequestMapping(value="/getShiftss/json", method = RequestMethod.GET, headers="Accept=*/*",  produces="application/json")
	public @ResponseBody List<CalendarJSONShow> getShiftsInJSON(HttpSession session){
		User u = (User) session.getAttribute("logedUser");
		Staff s = this.personService.getStaff(u.getEmail());
		Restaurant restaurant = this.personService.getRestaurant(s.getRestaurant().getId());

		Set<Staff> temp = restaurant.getStaff();
		List<Staff> staffList = new ArrayList<Staff>();
		
		for (Staff st : temp) {
			if (st.getRole().getId() != 5) {
				staffList.add(st);
			}
		}
		
		List<CalendarJSONShow> jsonShowCal = new ArrayList<CalendarJSONShow>();
		for(Staff sts:staffList){
			for (Shift_schedule ss:sts.getShift_schedule()){
				jsonShowCal.add(getJSONFormat(ss,sts));
			}
		}
		return jsonShowCal;
	}
	
	public CalendarJSONShow getJSONFormat(Shift_schedule smena,Staff s){
		CalendarJSONShow cs = new CalendarJSONShow();
		char i = Character.toUpperCase(s.getFirstName().charAt(0));
		char p = Character.toUpperCase(s.getLastName().charAt(0));
		cs.setTitle(smena.getShift_entry()+" "+i+"."+p+". "+s.getRole().getRoleName());
		cs.setStart(smena.getShift_date());
		return cs;
	}
}
