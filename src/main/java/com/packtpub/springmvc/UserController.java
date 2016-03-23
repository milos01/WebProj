package com.packtpub.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packtpub.springmvc.model.Person;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class UserController {

	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerUser(@ModelAttribute("person") Person p) {
		this.personService.addPerson(p);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "register";
    }
}
