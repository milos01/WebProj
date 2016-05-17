package com.packtpub.springmvc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Table_schedule;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class RegisterContoller {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	
	@RequestMapping(value = "restaurant/{id}/check",  method = RequestMethod.POST)
	public String restaurantReserve(@PathVariable(value = "id") int id, @RequestParam("guestNum")int guestNum, @RequestParam("res_date")String res_date, @RequestParam("res_from")int res_from, @RequestParam("res_to")int res_to, RedirectAttributes redirectAttributes, HttpSession session,Model model, Map<String, Object> map) {
		System.out.println(guestNum+" "+res_date+" "+res_from+" "+res_to);
		List<Table_schedule> checkedTables = personService.checkForFreeTables(res_date, res_from, res_to, guestNum);
		redirectAttributes.addFlashAttribute("checkedTables", checkedTables);
		return "redirect:/restaurant/"+id;

	}
}
