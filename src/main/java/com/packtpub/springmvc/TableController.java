package com.packtpub.springmvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.TablePosition;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class TableController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	
	@RequestMapping(value="/savePositions", method = RequestMethod.POST)
	public String  getShiftsInJSON(HttpSession session,@RequestBody List<TablePosition> tableposition,RedirectAttributes redirectAttributes){
		
		for (TablePosition t:tableposition){
			//System.out.println(t.getRow() + " " + t.getCol() + " " +t.getId());
			this.personService.updateTablePosition(t);
		}
		redirectAttributes.addFlashAttribute("savedPositions", "Positions successfully saved!");
		return "redirect:/home";
	}
	
	@RequestMapping(value="/addNewTable", method = RequestMethod.POST)
	public String addNewTable(@RequestParam("guest_num") String guest_num,@RequestParam("reon_id") String reon_id,@RequestParam("id") String idRest,RedirectAttributes redirectAttributes){
		Restaurant restaurant = this.personService.getRestaurant(Integer.parseInt(idRest));
		List<TableOne> tables = new ArrayList<TableOne>();
		Set<Reon>listareona = restaurant.getReons();
		Reon reon = null;
		for (Reon r:listareona){
			if (r.getReon_num()==Integer.parseInt(reon_id) && r.getRestaurant().getId()==restaurant.getId()){
				reon = r;
			}
			for (TableOne tb:r.getTables()){
				tables.add(tb);
			}
		}
		
		List<Integer> tempPosition = new ArrayList<Integer>();
		for (TableOne tp:tables){
			tempPosition.add(tp.getTableposition().getRow());
		}
		Collections.sort(tempPosition);
		int row = tempPosition.get((tempPosition.size()-1)) + 1;
		TablePosition tp = new TablePosition();
		tp.setCol(1); tp.setRow(row); tp.setSize_x(1); tp.setSize_y(1);
		this.personService.addNeWTablePosition(tp);
		
		TableOne to =  new TableOne();
		to.setGuest_num(Integer.parseInt(guest_num));
		to.setReon_id(reon);
		to.setReserved(0);
		to.setTableposition(tp);
		this.personService.addNewTable(to);
		redirectAttributes.addFlashAttribute("newTableAdded", "Table successfully added");
		return "redirect:/home";
	}

}
