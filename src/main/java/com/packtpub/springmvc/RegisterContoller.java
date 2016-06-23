package com.packtpub.springmvc;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
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
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	@RequestMapping(value = "restaurant/{id}/check",  method = RequestMethod.POST)
	public String restaurantReserve(@PathVariable(value = "id") int id, @RequestParam("res_date")String res_date, @RequestParam("res_from")String res_from, @RequestParam("res_to")String res_to, RedirectAttributes redirectAttributes, HttpSession session,Model model, Map<String, Object> map) {
		System.out.println(0+" "+res_date+" "+res_from+" "+res_to);
		
		String [] dateParts = res_date.split("-");
		String year = dateParts[0];
		String month = dateParts[1];
		String date = dateParts[2];
		System.err.println(year);
		
		String [] timeParts = res_from.split(":");
		String hour = timeParts[0];
		String minute = timeParts[1];
		System.err.println(hour + ' ' + minute);
		
		Time now = new Time(13,00,00);
		Time somwhere = new Time(Integer.parseInt(hour), Integer.parseInt(minute),00);
		System.err.println(now.getTime());
		
		System.err.println(somwhere);
//		System.err.println(now.getClass().getName());
		List<Table_schedule> checkedTables = personService.checkForFreeTables(res_date, res_from, res_to, 0);
		System.err.println(checkedTables.size());
		redirectAttributes.addFlashAttribute("checkedTables", checkedTables);
//		eventPublisher.publishEvent(new OnRegistrationCompleteEvent("kitaaaa"));
		return "redirect:/restaurant/"+id;

	}
}
