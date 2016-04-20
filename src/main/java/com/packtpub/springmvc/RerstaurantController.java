package com.packtpub.springmvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class RerstaurantController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping("/restaurant/{id}")
	public String restaurantHome(@PathVariable(value = "id") final int id, RedirectAttributes redirectAttributes,Model model, HttpSession session) {
		if(null == session.getAttribute("logedUser")){
			return "redirect:/";
		}
		redirectAttributes.addFlashAttribute("id", id);
		Restaurant restaurant = this.personService.getRestaurant(id);
		model.addAttribute("restaurant", restaurant);
		return "restaurant";

	}
}
