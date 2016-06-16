package com.packtpub.springmvc;

import java.sql.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.Table_schedule;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class TableReservationController {
	private PersonService personService;
	
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	@RequestMapping(value = "/restaurant/{id}/reserveTable", method = RequestMethod.POST)
	public String reserveTable(@RequestParam("res_from")int resFrom, @RequestParam("res_to")int resTo, @RequestParam("resId")int resId,@RequestParam("tableId") int table_id, @RequestParam("guestNum") int guestNum, @PathVariable(value = "id") final String idd, HttpSession session){
		System.out.println(resFrom + " " + resTo + " " + table_id + " " + idd);
		TableOne to = personService.findTableOn(table_id);
		Table_schedule ts = new Table_schedule();
		ts.setDate(new Date(1111,11,11));
		ts.setReserved_from(resFrom);
		ts.setReserved_to(resTo);
		ts.setTable(to);
		personService.addTableSchedule(ts);
		
		User user = (User)session.getAttribute("logedUser");
		Restaurant rest = personService.getRestaurant(resId);
		Reservation res = new Reservation();
		res.setUser(user);
		res.setTable_schedule(ts);
		res.setRes_restaurant(rest);
		res.setPeople_num(guestNum);
		personService.addReservations(res);
		
		return "redirect:/restaurant/"+idd;
	}	
}
