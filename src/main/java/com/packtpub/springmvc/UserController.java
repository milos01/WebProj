package com.packtpub.springmvc;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.SpringVersion;

import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;
import com.packtpub.springmvc.pojo.UserIdPojo;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class UserController {

	private PersonService personService;
	
	
	final Calendar cal = Calendar.getInstance();

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@Autowired
    private JavaMailSender mailSender;

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String userLogin(@RequestParam String loginEmail,@RequestParam String loginPassword, Model model, RedirectAttributes redirectAttributes, HttpSession session,HttpServletRequest request){
		if(loginEmail.equals("") || loginPassword.equals("")){
			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
			return "redirect:/";
		}
		User u = personService.loginUser(loginEmail, loginPassword);
		if (u == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "User not exists!");
			return "redirect:/";
		}else{
			if (u.getPassword().equals(loginPassword)) {
				
				session.setAttribute("logedUser",u);
				return "redirect:/home";
			}else{
				redirectAttributes.addFlashAttribute("errorMessage", "Check your password!");
				return "redirect:/";
			}
		}
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(  @ModelAttribute("User") @Valid User u, BindingResult bindres, final HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (bindres.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindres);
			return "redirect:/";
		}else{
			System.out.println("NEMA ERORRA");
		}
		
		this.personService.addPerson(u);
		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		System.out.println(appUrl);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(u, request.getLocale(), appUrl));
        redirectAttributes.addFlashAttribute("successMessage", "Activation mail was send to privited mail adress");
        return "redirect:/";
	}
	
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public String updateUser( @ModelAttribute("User") User u,@PathVariable("id")int id , RedirectAttributes redirectAttributes, HttpSession session){
		personService.updatePerson((User)session.getAttribute("logedUser"),u);
		return "redirect:/home";
	}
	 
	
	@RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(final Locale locale, final Model model, @RequestParam("token") final String token, RedirectAttributes redirectAttributes) {
		System.out.println(token+"yo men");
        final VerificationToken verificationToken = personService.getVerificationToken(token);
        if (verificationToken == null) {
            final String message = "Invalid token";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/";
        }
        System.out.println(verificationToken.getUser().getEmail());
        final User user = verificationToken.getUser();
        
        System.out.println(verificationToken.getExpiryDate());
        System.out.println(cal.getTime());
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "Expired token");
            model.addAttribute("expired", true);
            redirectAttributes.addFlashAttribute("token", token);
            user.setTokenExpired(true);
//            personService.updatePerson(user,user.getId());
            return "redirect:/";
        }
        if(!user.isEnabled()){
        	 user.setEnabled(true);
//             personService.updatePerson(user, user.getId());
             System.out.println("promenjen");
             redirectAttributes.addFlashAttribute("successMessage", "You activated account");
     		return "redirect:/";
        }else{
        	System.out.println("vec postoji");
        	redirectAttributes.addFlashAttribute("successMessage", "You alerady activated account");
        	
     		return "redirect:/";
        }
       
    }
	
	@RequestMapping(value = "/resendRegistrationToken/{token}", method = RequestMethod.GET)
    public String resendRegistrationToken(@PathVariable("token")String token, final HttpServletRequest request) {
		final VerificationToken verificationToken = personService.getVerificationToken(token);
		
		cal.add(Calendar.DATE, 1);
		verificationToken.setExpiryDate(cal.getTime());
		cal.add(Calendar.DATE, -1); 
		personService.updateVerificationToken(verificationToken);
		
		User user = verificationToken.getUser();
		user.setTokenExpired(false);
//        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
//        final User user = userService.getUser(newToken.getToken());
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        final SimpleMailMessage email = constructResendVerificationTokenEmail(appUrl, request.getLocale(), user, token);
        mailSender.send(email);
		return "redirect:/";
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session ) {
        session.removeAttribute("logedUser");
        return "redirect:/";
    }
	
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	
    public @ResponseBody String findUser(@RequestBody UserIdPojo userpojo) {
		System.err.println(userpojo.getUserId() + "aaaaaaaa");
		return "{\"success\":true}";
    }
	
	 private final SimpleMailMessage constructResendVerificationTokenEmail(final String contextPath, final Locale locale, final User user, final String token) {
	        final String confirmationUrl = contextPath + "/regitrationConfirm.html?token=" + token;
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setSubject("Resend Registration Token");
	        email.setText("New token." + " \r\n" + confirmationUrl);
	        email.setTo(user.getEmail());
	        email.setFrom("milos94@gmail.com");
	        return email;
	    }
}
