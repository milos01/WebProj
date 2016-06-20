package com.packtpub.springmvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.ReonTypes;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class StaffController {
	private PersonService personService;
	
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	
	@RequestMapping(value = "/tableSchedule", method = RequestMethod.GET)
	public String tableSchedule(Model model, HttpSession session) throws ParseException{
		if (null == session.getAttribute("logedUser")) {
			return "redirect:/";
		}
		User u = (User) session.getAttribute("logedUser");
		Staff s = this.personService.getStaff(u.getEmail());
		Restaurant restaurant = this.personService.getRestaurant(s.getRestaurant().getId());
		Set<Shift> shiftsRest = restaurant.getShifts();
		
		List<TableOne> tables = new ArrayList<TableOne>();
		Set<Reon>listareona = restaurant.getReons();
		List<ReonTypes> tipoviReona = new ArrayList<ReonTypes>();
		
		for (Reon r:listareona){
			for (TableOne tb:r.getTables()){
				tables.add(tb);
			}
			
			for(ReonTypes rt:r.getReonTypes()){
				tipoviReona.add(rt);
			}
		}
		
		Set<Shift_schedule> allShifts = s.getShift_schedule();
		List<TableOne> workingTables = new ArrayList<TableOne>();
		for (Shift_schedule ss : allShifts){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Date todayWithZeroTime = dateFormat.parse(dateFormat.format(date));
			if(ss.getShift_date().toString().equals(dateFormat.format(date))){
				for(Shift sh : s.getRestaurant().getShifts()){
					if(sh.getShift_entry().equals(ss.getShift_entry())){
						System.out.println(sh.getEnd_shift().getTime()+todayWithZeroTime.getTime());
						Date begin = new Date(date.getYear(),date.getMonth(),date.getDate(),sh.getStart_shift().getHours(),sh.getStart_shift().getMinutes(),sh.getStart_shift().getSeconds());
						Date end = new Date(date.getYear(),date.getMonth(),date.getDate(),sh.getEnd_shift().getHours(),sh.getEnd_shift().getMinutes(),sh.getEnd_shift().getSeconds());
						if(date.getTime()>=begin.getTime() && date.getTime()<end.getTime()){
						//if(((date.getHours()*60+date.getMinutes())>=(sh.getStart_shift().getHours()*60+sh.getStart_shift().getMinutes())) && ((date.getHours()*60+date.getMinutes())<(sh.getEnd_shift().getHours()*60+sh.getEnd_shift().getMinutes()))){
						//if(date.getTime()>=(sh.getStart_shift().getTime()+todayWithZeroTime.getTime()) && date.getTime()<(sh.getEnd_shift().getTime()+todayWithZeroTime.getTime())){	
							int num = ss.getReonNum();
							for(Reon r : listareona){
								if(r.getReon_num()==num){
									workingTables.addAll(r.getTables());
								}
							}
						}
					}
				}
			}
		}
		
		model.addAttribute("tables",tables);
		model.addAttribute("workingTables", workingTables);
		model.addAttribute("restoran", restaurant);
		
		
		return "tableSchedule";
	}
	
}
