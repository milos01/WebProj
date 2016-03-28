package com.packtpub.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam String username,@RequestParam String password, Model model, RedirectAttributes redirectAttributes){
		if(username.equals("") || password.equals("")){
			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
		}
//		personService.loginUser(username, password);
		return "redirect:/";
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
