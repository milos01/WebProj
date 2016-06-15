package com.packtpub.springmvc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.FoodItem;
import com.packtpub.springmvc.model.FoodListItem;
import com.packtpub.springmvc.model.GrocaryList;
import com.packtpub.springmvc.model.Offer;
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
	
	@RequestMapping(value="/addNewItemToCart", method = RequestMethod.POST)
	public String newItemToCart(@ModelAttribute("item") FoodItem fi,@RequestParam("quantity") String kolicina,@RequestParam("grocery_id") String gr_id,RedirectAttributes redirectAttributes){
		
		
		FoodListItem fli =  new FoodListItem();
		fli.setFooditem(fi);
		fli.setQuantity(Integer.parseInt(kolicina));
		GrocaryList gl = this.personService.findGrocery(Integer.parseInt(gr_id));
		fli.setGrocaryList(gl);
		this.personService.addFoodListItem(fli);
		redirectAttributes.addFlashAttribute("itemToCardAdded","Item successfully added to list!");
		return "redirect:/home";
	}
	
	@RequestMapping(value="/newGroceryList", method = RequestMethod.POST)
	public String newGroceryList(@RequestParam("GLfrom") String dateFrom,@RequestParam("GLto") String dateTo,@RequestParam("restaurant_id") String idRest,RedirectAttributes redirectAttributes) throws ParseException{
		
		GrocaryList gl = new GrocaryList();
		Date from = getFormatedDate(dateFrom);
		Date to = getFormatedDate(dateTo);
		Restaurant r = this.personService.getRestaurant(Integer.parseInt(idRest));
		gl.setGLfrom(from);
		gl.setGLto(to);
		gl.setRestaurant(r);
		
		this.personService.addGroceryList(gl);
		redirectAttributes.addFlashAttribute("GroceryAdded","New Grocery list successfully added!");
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/restaurant/{id}/createOffer", method = RequestMethod.POST)
	public String createOffers(@PathVariable("id") String idRest,@RequestParam("price") String price,@RequestParam("warranty") String warr,@RequestParam("deadline") String deadline,@RequestParam("groc_id") String groc_id,HttpSession ses,RedirectAttributes redirectAttributes) throws ParseException {
		
		
			User u = (User) ses.getAttribute("logedUser");
			Offer temp = this.personService.getOffer(Integer.parseInt(groc_id), u.getId());
			if (temp!=null){
				redirectAttributes.addFlashAttribute("addedNewOffer", "Offer already created!");
				return "redirect:/restaurant/"+idRest;
			}
			Offer of =  new Offer();
			GrocaryList gl = this.personService.findGrocery(Integer.parseInt(groc_id));
			Date date = getFormatedDate(deadline);
			of.setGrocaryList(gl);
			of.setUser(u);
			of.setPrice(Integer.parseInt(price));
			of.setAccepted(2);
			of.setDeadline(date);
			of.setWarranty(Integer.parseInt(warr));
			redirectAttributes.addFlashAttribute("addedNewOffer", "Offer successfully created!");
			this.personService.createOffer(of);
		return "redirect:/restaurant/"+idRest;
	}

	@RequestMapping(value = "/bidderPasswordChange", method = RequestMethod.POST)
	public String changeBidderPass(@RequestParam("oldPass") String oldpass,@RequestParam("newPass1") String newpass1,@RequestParam("newPass2") String newpass2,RedirectAttributes redirectAttributes,HttpSession ses){
		
		User u = (User) ses.getAttribute("logedUser");
		
		if (!oldpass.equals(u.getPassword())){
			redirectAttributes.addFlashAttribute("wrongPass","Your old passwod is incorect!");
			return "redirect:/home";
		}
		
		if (!newpass1.equals(newpass2)){
			redirectAttributes.addFlashAttribute("wrongPass","Wrong repeated passwod!");
			return "redirect:/home";
		}
		
		u.setPassword(newpass1);
		this.personService.updatePerson(u);
		redirectAttributes.addFlashAttribute("passwodChanged","Passwod successfully changed!");
		return "redirect:/home";
	}

	@RequestMapping(value = "/bidderProfUpdate", method = RequestMethod.POST)
	public String updateBidderProf(@RequestParam("fName") String name,@RequestParam("lName") String last,@RequestParam("NewMail") String mail,RedirectAttributes redirectAttributes,HttpSession ses){
		
		User u = (User) ses.getAttribute("logedUser");
		u.setFirstName(name);
		u.setLastName(last);
		u.setEmail(mail);
		this.personService.updatePerson(u);
		redirectAttributes.addFlashAttribute("bidderProfUp","Profil successfully updated!");
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/restaurant/bidderPasswordChange", method = RequestMethod.POST)
	public String changeBidderPass2(@RequestParam("oldPass") String oldpass,@RequestParam("newPass1") String newpass1,@RequestParam("newPass2") String newpass2,RedirectAttributes redirectAttributes,HttpSession ses){
		User u = (User) ses.getAttribute("logedUser");
		
		if (!oldpass.equals(u.getPassword())){
			redirectAttributes.addFlashAttribute("wrongPass","Your old passwod is incorect!");
			return "redirect:/home";
		}
		
		if (!newpass1.equals(newpass2)){
			redirectAttributes.addFlashAttribute("wrongPass","Wrong repeated passwod!");
			return "redirect:/home";
		}
		
		u.setPassword(newpass1);
		this.personService.updatePerson(u);
		redirectAttributes.addFlashAttribute("passwodChanged","Passwod successfully changed!");
		return "redirect:/restaurant";
	}
	
	
	@RequestMapping(value = "/restaurant/{id}/changeOffer", method = RequestMethod.POST)
	public String changeOffer(@ModelAttribute("offer") Offer offer,@PathVariable("id") String restId,@RequestParam("grocaryId") String grocaryId,RedirectAttributes redirectAttributes,HttpSession ses){
		User u = (User) ses.getAttribute("logedUser");
		
		GrocaryList gl = this.personService.findGrocery(Integer.parseInt(grocaryId));
		if (offer.getAccepted()==0 || offer.getAccepted()==1){
			redirectAttributes.addFlashAttribute("updatedOffer","Currently it can not be changed!");
			return "redirect:/restaurant/"+restId;
		}
		java.util.Date td = new java.util.Date();
		Date today = new Date(td.getTime());
		System.out.println(today + "  GL date: "+gl.getGLto());
		if(today.compareTo(gl.getGLto())>0){
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			redirectAttributes.addFlashAttribute("updatedOffer","Date for changing bids has expired!");
			return "redirect:/restaurant/"+restId;
		}
		
		offer.setGrocaryList(gl);
		offer.setUser(u);
		this.personService.updateOffer(offer);
		redirectAttributes.addFlashAttribute("updatedOffer","Offer successfully changed!");
		return "redirect:/restaurant/"+restId;
	}
	
	
	@RequestMapping(value = "/acceptOffer/{accepted}/{offerId}", method = RequestMethod.GET)
	public String acceptOffer(@PathVariable("accepted") String accpeted,@PathVariable("offerId") String idOff,RedirectAttributes redirectAttributes){
		System.out.println("EEEEsssadsasadsafasfasgasgsagsagas as gsag aEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		Offer of = this.personService.findOffer(Integer.parseInt(idOff));
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		if(Integer.parseInt(accpeted)==0){
			of.setAccepted(0);
			this.personService.updateOffer(of);
			redirectAttributes.addFlashAttribute("acceptedOffer","Offer successfully beated off!");
			return "redirect:/home";
		}
		else{
			of.setAccepted(1);
			this.personService.updateOffer(of);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			List<Offer> offers = this.personService.getOffers(of.getGrocaryList().getId());
			for(Offer off:offers){
				if (off.getId()!=of.getId()){
					off.setAccepted(0);
					this.personService.updateOffer(off);
				}
			}
			redirectAttributes.addFlashAttribute("acceptedOffer","Offer successfully Accepted!");
			return "redirect:/home";
		}
	}
	
	public Date getFormatedDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(date);
		Date sql = new Date(parsed.getTime());
		return sql;
	}
}
