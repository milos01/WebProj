package com.packtpub.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RerstaurantController {
	@RequestMapping("/restaurant")
	public String showRestaurant(){
		return "restaurant";
		
	}
	@RequestMapping("/restaurant/{id}")
	public String restaurantHome(@PathVariable(value="id") final String id, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("id", id);
		return "restaurant";
		
	}
}
