package com.packtpub.springmvc;

import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class UserController {

	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	@Autowired
    private ApplicationEventPublisher eventPublisher;

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam String username,@RequestParam String password, Model model, RedirectAttributes redirectAttributes){
		if(username.equals("") || password.equals("")){
			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
		}
//		personService.loginUser(username, password);
		return "redirect:/";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("User") User u, final HttpServletRequest request,RedirectAttributes redirectAttributes) {
		this.personService.addPerson(u);
		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println(appUrl);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(u, request.getLocale(), appUrl));
        redirectAttributes.addFlashAttribute("successMessage", "Activation mail was send to privited mail adress");
        return "redirect:/";
	}
	
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token, RedirectAttributes redirectAttributes) {
		System.out.println(token);
        final VerificationToken verificationToken = personService.getVerificationToken(token);
        if (verificationToken == null) {
            final String message = "Invalid token";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/";
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        System.out.println(verificationToken.getExpiryDate());
        System.out.println(cal.getTime());
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "Expired token");
            model.addAttribute("expired", true);
            model.addAttribute("token", token);
            user.setTokenExpired(true);
            personService.updatePerson(user);
            return "redirect:/";
        }
        if(!user.isEnabled()){
        	 user.setEnabled(true);
             personService.updatePerson(user);
             System.out.println("promenjen");
             redirectAttributes.addFlashAttribute("successMessage", "You activated account");
     		return "redirect:/";
        }else{
        	System.out.println("vec postoji");
        	redirectAttributes.addFlashAttribute("successMessage", "You alerady activated account");
     		return "redirect:/";
        }
       
    }
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new User());
        model.addAttribute("listPersons", this.personService.listPersons());
        return "register";
    }
}
