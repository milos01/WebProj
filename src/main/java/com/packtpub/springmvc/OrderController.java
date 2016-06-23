package com.packtpub.springmvc;

import java.sql.Date;
import java.text.ParseException;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Order;
import com.packtpub.springmvc.model.OrderedItem;
import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class OrderController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping(value = "/restaurant/{id}/user/{idd}/from/{fromuser}/placeOrder", method = RequestMethod.POST)
	public String placeOrder(@PathVariable(value = "id") final int resId, @PathVariable(value = "idd") final int userId,
			@RequestParam(value = "OnTime", required = false) long[] isOntime, @RequestParam(value = "meals", required = false) String[] meals ) {
		
			User user = (User) personService.findPerson(userId);

			Reservation res = (Reservation) personService.findReservation(resId);

			Order order = new Order();
			order.setUser(user);
			order.setReservation(res);
			if (isOntime != null) {
				order.setRadyOnTime(1);
			}else{
				order.setRadyOnTime(0);
			}
			order.setRate(0);
			order.setAccepted(0);
			
			personService.addOrder(order);
			
			for (int i = 0; i < meals.length; i++) {
				String[] tokens = meals[i].split("-");
				int foodType = Integer.parseInt(tokens[0]);
				int foodId = Integer.parseInt(tokens[1]);
				
				OrderedItem item = new OrderedItem();
				item.setOrder(order);
				item.setFoodType(foodType);
				item.setFoodId(foodId);
				item.setReady(0);
				item.setQuantity(2);
				
				personService.addOrderedItem(item);
			}
		
		return "redirect:/home";
	}
	@RequestMapping(value = "/restaurant/{id}/placeOrderSecond", method = RequestMethod.POST)
	public String placeOrderSecond(
			@RequestParam(value = "resId") int resId, @RequestParam(value = "OnTime", required = false) long[] isOntime, @RequestParam(value = "meals", required = false) String[] meals , HttpSession session) {
		
			User user = (User) session.getAttribute("logedUser");
			
			Reservation res = (Reservation) personService.findReservation(resId);
			
			
			
			Order order = new Order();
			order.setUser(user);
			order.setReservation(res);
			if (isOntime != null) {
				order.setRadyOnTime(1);
			}else{
				order.setRadyOnTime(0);
			}
			order.setRate(0);
			order.setAccepted(0);
			
			personService.addOrder(order);
			
			for (int i = 0; i < meals.length; i++) {
				String[] tokens = meals[i].split("-");
				int foodType = Integer.parseInt(tokens[0]);
				int foodId = Integer.parseInt(tokens[1]);
				
				OrderedItem item = new OrderedItem();
				item.setOrder(order);
				item.setFoodType(foodType);
				item.setFoodId(foodId);
				item.setReady(0);
				item.setQuantity(2);
				
				personService.addOrderedItem(item);
			}
		
		return "redirect:/home";
	}
}
