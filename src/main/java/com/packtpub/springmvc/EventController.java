package com.packtpub.springmvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packtpub.springmvc.model.Event;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.pojo.NewEventPojo;
import com.packtpub.springmvc.pojo.NewRestaurantPojo;
import com.packtpub.springmvc.service.PersonService;

@RestController
public class EventController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	@RequestMapping(value="/addEvent", method = RequestMethod.POST ,headers="Accept=*/*")
	public ResponseEntity <Event>addEvent(@RequestBody NewEventPojo rpp,HttpSession session){
		
		Restaurant res = this.personService.findRestaurant(rpp.getResid());
		
		Event eve = new Event();
		eve.setDate(rpp.getExdate());
		eve.setDescription(rpp.getDescription());
		eve.setPicture(rpp.getPicture());
		eve.setRestaurant(res);
		
		this.personService.addEvent(eve);
		
		return new ResponseEntity<Event> (eve, HttpStatus.OK);
	}

}
