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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.packtpub.springmvc.model.Recension;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.pojo.NewUserPojo;
import com.packtpub.springmvc.pojo.RemoveRecensionPojo;
import com.packtpub.springmvc.pojo.UserIdPojo;
import com.packtpub.springmvc.pojo.userParamsPojo;
import com.packtpub.springmvc.service.PersonService;

@RestController
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

	@RequestMapping(value="/login", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<User> userLogin(@RequestBody userParamsPojo upp, HttpSession session){
//		if(loginEmail.equals("") || loginPassword.equals("")){
//			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
////			return "redirect:/";
//		}
		User u = personService.loginUser(upp.getLoginEmail(), upp.getLoginPassword());
		if (u == null) {
//			redirectAttributes.addFlashAttribute("errorMessage", "User not exists!");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else{
			if (u.getPassword().equals(upp.getLoginPassword())) {
				
				session.setAttribute("logedUser",u);
				return new ResponseEntity<User>(u, HttpStatus.OK);
			}else{
//				redirectAttributes.addFlashAttribute("errorMessage", "Check your password!");
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	@RequestMapping(value="/getuser", method = RequestMethod.GET ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<User> getUser(HttpSession session){
//		if(loginEmail.equals("") || loginPassword.equals("")){
//			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
////			return "redirect:/";
//		}
		User u = (User)session.getAttribute("logedUser");
		if (u == null) {
//			redirectAttributes.addFlashAttribute("errorMessage", "User not exists!");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else{
			
				return new ResponseEntity<User>(u, HttpStatus.OK);
			
		}
	}
	
	@RequestMapping(value="/getuser/{id}", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<User> getusser(@PathVariable(value = "id") final int id,HttpSession session){
//		if(loginEmail.equals("") || loginPassword.equals("")){
//			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
////			return "redirect:/";
//		}
		
//		if (u == null) {
////			redirectAttributes.addFlashAttribute("errorMessage", "User not exists!");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}else{
//			
//				return new ResponseEntity<User>(u, HttpStatus.OK);
//			
//		}
		System.err.println(id);
		User u = this.personService.findUser(id);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<User> registerUser(@RequestBody NewUserPojo rpp,HttpSession session){
		User u = new User();
		Role role = personService.getRole(2);
		System.err.println(rpp.getLastName());
		u.setRole(role);
		u.setEmail(rpp.getEmail());
		u.setFirstName(rpp.getFirstName());
		u.setLastName(rpp.getLastName());
		u.setPassword(rpp.getPassword());
		this.personService.addPerson(u);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
	
	@RequestMapping(value="/restaurant/{id}/user/{userid}/from/{fromId}/loginSecond", method = RequestMethod.POST)
	public String userLoginSecond(@PathVariable(value = "id") final String resId,@PathVariable(value = "userid") final String userid,@PathVariable(value = "fromId") final String fromId,@RequestParam String loginEmailSecond,@RequestParam String loginPasswordSecond,@RequestParam String resid, Model model, RedirectAttributes redirectAttributes, HttpSession session,HttpServletRequest request){
		if(loginEmailSecond.equals("") || loginPasswordSecond.equals("")){
			redirectAttributes.addFlashAttribute("errorMessage", "Username and password must not be empty");
			return "redirect:/restaurant/"+resId+"/reservation/"+resid+"/user/"+userid+"/from/"+fromId+"/order";
		}
		User u = personService.loginUser(loginEmailSecond, loginPasswordSecond);
		if (u == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "User not exists!");
			return "redirect:/restaurant/"+resId+"/reservation/"+resid+"/user/"+userid+"/from/"+fromId+"/order";
		}else{
			if (u.getPassword().equals(loginPasswordSecond)) {
				
				session.setAttribute("logedUser",u);
				return "redirect:/restaurant/"+resId+"/reservation/"+resid+"/user/"+userid+"/from/"+fromId+"/order";
			}else{
				redirectAttributes.addFlashAttribute("errorMessage", "Check your password!");
				return "redirect:/restaurant/"+resId+"/reservation/"+resid+"/user/"+userid+"/from/"+fromId+"/order";
			}
		}
	}
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String registerUser(  @ModelAttribute("User") @Valid User u, BindingResult bindres, final HttpServletRequest request,RedirectAttributes redirectAttributes) {
//		if (bindres.hasErrors()) {
//			redirectAttributes.addFlashAttribute("errors", bindres);
//			return "redirect:/";
//		}else{
//			System.out.println("NEMA ERORRA");
//		}
//		
//		Role role = personService.getRole(2);
//		u.setRole(role);
//		this.personService.addPerson(u);
//		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//		System.out.println(appUrl);
//		eventPublisher.publishEvent(new OnRegistrationEvent(u, request.getLocale(), appUrl));
//        redirectAttributes.addFlashAttribute("successMessage", "Activation mail was send to privited mail adress");
//        return "redirect:/";
//	}
	
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public String updateUser( @ModelAttribute("User") User u,@PathVariable("id")int id , RedirectAttributes redirectAttributes, HttpSession session){
		personService.updatePerson((User)session.getAttribute("logedUser"),u);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<User> removeRecension(HttpSession session){
			
			session.removeAttribute("logedUser");
			return new ResponseEntity<User>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/restaurant/{id}/user/{userid}/from/{fromId}/logoutSecond", method = RequestMethod.POST)
    public String logout2(@PathVariable("id")String resid,@PathVariable("userid")String userid,@PathVariable("fromId")String fromid ,@RequestParam("resid") String ressid,HttpSession session) {
        session.removeAttribute("logedUser");
        
        return "redirect:/restaurant/"+resid+"/reservation/"+ressid+"/user/"+userid+"/from/"+fromid+"/order";
    }
	
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	
    public @ResponseBody String findUser(@RequestBody UserIdPojo userpojo) {
		System.out.println(userpojo.getUserId());
		if(personService.findUserByEmail(userpojo.getUserId())){
			System.out.println("true");
			return "{\"success\":true}";
		}else{
			System.out.println("false");
			return "{\"success\":false}";
		}
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
