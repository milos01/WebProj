package com.packtpub.springmvc;

import java.security.Principal;
import java.sql.Date;
import java.util.Locale;

import javax.mail.Session;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.Table_schedule;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
@SessionAttributes("logedUser")
public class TableReservationController {
	private PersonService personService;
	
	
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@RequestMapping(value = "/restaurant/{id}/reserveTable", method = RequestMethod.POST)
	public String reserveTable(@RequestParam("res_from")int resFrom, @RequestParam(value = "userIds") int[] userIds, @RequestParam("res_to")int resTo, @RequestParam("resId")int resId,@RequestParam("tableId") int table_id, @RequestParam("guestNum") int guestNum, @PathVariable(value = "id") final String idd, HttpSession session){
		System.out.println(resFrom + " " + resTo + " " + table_id + " " + idd );
		
		TableOne to = personService.findTableOn(table_id);
		Table_schedule ts = new Table_schedule();
		System.err.println(ts.toString());
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
		
		for (int i = 0; i < userIds.length; i++) {
			User us = personService.findPerson(userIds[i]);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(us, res,user));
		}
		return "redirect:/restaurant/"+idd;
	}
	@RequestMapping(value = "/restaurant/{id}/reservation/{resid}/user/{userid}/from/{fromId}/order", method = RequestMethod.GET)
    public String confirmRegistration(Model model, @PathVariable(value = "id") final int id,@PathVariable(value = "resid") final String resid, @PathVariable(value = "fromId") final String fromId, @PathVariable(value = "userid") final String userid, final HttpServletRequest request, HttpSession session) {
		System.err.println(id + " "+resid);
		model.addAttribute("rid", id);
		model.addAttribute("resid", resid);
		model.addAttribute("userid", userid);
		model.addAttribute("fromId", fromId);
//		System.err.println();
//		User luser = (User)session.getAttribute("logedUser");
		
//		System.err.println(luser.toString());
//		System.err.println(request.getSession().getAttribute("logedUser"))
		return "redirect:/restaurant/"+Integer.toString(id)+"/user/"+userid+"/from/"+fromId+"/order";
	}
	
	@RequestMapping(value = "/restaurant/{id}/user/{userid}/from/{fromId}/order", method = RequestMethod.GET)
	public String home(Model model, RedirectAttributes redirectAttributes, @RequestParam("rid") int rid, @RequestParam("userid") int userid, @RequestParam("fromId") int fromId, @RequestParam("resid") int resid,  HttpSession session) {

//		System.err.println(this.getSession(session).getFirstName());
		System.err.println(resid);
		int checkUser = 0;
		User user = (User)session.getAttribute("logedUser");
		if (user != null) {
			model.addAttribute("logedUser", user);
			checkUser = 1;
		}
		model.addAttribute("reastaurantId", rid);
		model.addAttribute("reservation",personService.findReservation(resid));
		
		model.addAttribute("user", personService.findPerson(fromId));
		model.addAttribute("checkUser",checkUser);
//		System.err.println(session.getAttribute("logedUser"));
//		redirectAttributes.addFlashAttribute("resid", resid);
		return "order";
	}
	
	@RequestMapping(value = "/restaurant/{id}/user/{userid}/from/{fromId}/logout", method = RequestMethod.GET)
	public void logoutUser(HttpSession session) {
//		session.invalidate();
		session.removeAttribute("logedUser");
//		User u = (User)session.getAttribute("logedUser");
//		System.err.println(u.getEmail());
		
//		return "redirect:/restaurant/1/reservation/5/user/1/from/3/order";
	}
	
	public User getSession(HttpSession session){
		return (User)session.getAttribute("logedUser");
	}
}
