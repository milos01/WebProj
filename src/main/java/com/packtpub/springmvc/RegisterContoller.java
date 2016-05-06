package com.packtpub.springmvc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class RegisterContoller {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	
	@RequestMapping("restaurant/{id}/reserve")
	public String restaurantReserve(@PathVariable(value = "id") int id, @RequestParam("guestNum")int guestNum, @RequestParam("res_date")String res_date, @RequestParam("res_from")String res_from, @RequestParam("res_to")String res_to, RedirectAttributes redirectAttributes,Model model, HttpSession session) {
		System.out.println(guestNum+" "+res_date+" "+res_from+" "+res_to);
		personService.checkForFreeTables(res_date, res_from, res_to);
		return "redirect:/restaurant/"+id;

	}
}
