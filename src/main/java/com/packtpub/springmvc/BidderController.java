package com.packtpub.springmvc;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class BidderController {
	private PersonService personService;
	
	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps){
		this.personService=ps;
	}
	
	@RequestMapping(value="/registerBidder", method = RequestMethod.POST)
	public String bidderRegistration(@ModelAttribute("User") @Valid User u, BindingResult bindres, final HttpServletRequest request,RedirectAttributes redirectAttributes){
		if (bindres.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindres);
			return "redirect:/home";
		}
		
		List<Staff> staffs = this.personService.listStaffs();
		for(Staff ss : staffs){
			if(ss.getRole().getRoleName().equalsIgnoreCase("bidder")){
				if((ss.getFirstName().equalsIgnoreCase(u.getFirstName()) && ss.getLastName().equalsIgnoreCase(u.getLastName())) || ss.getEmail().equalsIgnoreCase(u.getEmail())){
					bindres.addError(new ObjectError("bidderName", "Bidder with that name (email) already exists"));
					redirectAttributes.addFlashAttribute("errors", bindres);
					return "redirect:/home";
				}
			}
		}
		
		u.setEnabled(true);
		u.setStarter_friend(new HashSet<User>());
		u.setTerminal_friend(new HashSet<User>());
		u.setTokenExpired(true);
		Role temp = this.personService.getRole(6);
		u.setRole(temp);
		this.personService.addPerson(u);
		redirectAttributes.addFlashAttribute("successMessage", "Bidder successfully registered");
		return "redirect:/home";
	}

}
