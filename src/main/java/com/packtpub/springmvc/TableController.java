package com.packtpub.springmvc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.packtpub.springmvc.model.Table_schedule;
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
	public @ResponseBody String  getShiftsInJSON(HttpSession session,@RequestBody List<TablePosition> tableposition,RedirectAttributes redirectAttributes){
		
		for (TablePosition t:tableposition){
			//System.out.println(t.getRow() + " " + t.getCol() + " " +t.getId());
			this.personService.updateTablePosition(t);
		}
		//redirectAttributes.addFlashAttribute("savedPositions", "Positions successfully saved!");
		return "success";
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
		int row = 0;
		if (tempPosition.size()!=0){
			row = tempPosition.get((tempPosition.size()-1)) + 1;
		}
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

	@RequestMapping(value="/deleteTable/{idTable}/{idRest}", method = RequestMethod.GET)
	public String deleteTable(@PathVariable("idTable") String idTable,@PathVariable("idRest") String idRest,RedirectAttributes redirectAttributes) throws ParseException{
		Restaurant restaurant = this.personService.getRestaurant(Integer.parseInt(idRest));
		List<TableOne> tables = new ArrayList<TableOne>();
		Set<Reon>listareona = restaurant.getReons();
		for (Reon r:listareona){
			for (TableOne tb:r.getTables()){
				tables.add(tb);
			}
		}
		TablePosition tp = this.personService.findTablePosition(Integer.parseInt(idTable));
		
		TableOne to = null;
		for(TableOne table:tables){
			if (table.getId()==tp.getId()){
				to = table;
			}
		}
		java.util.Date td = new java.util.Date();
		Date today = new Date(td.getTime());

		for(Table_schedule ts:to.getTables()){
			
			Date reservedDate = getFormatedDate(ts.getDate()+"");
			
			if (reservedDate.compareTo(today)>=0 && ts.getTable().getReserved()==1){
				redirectAttributes.addFlashAttribute("deletedTable", "Table is currently reserved, can not be deleted!");
				return "redirect:/home";
			}
			else if(reservedDate.compareTo(today)<0 && ts.getTable().getReserved()==1){
				this.personService.removeTableSchedule(ts.getId());
			}
			else {
				this.personService.removeTableSchedule(ts.getId());
			}
		}
		this.personService.removeTable(to);
		this.personService.removeTalbePosition(tp);
		redirectAttributes.addFlashAttribute("deletedTable", "Table successfully deleted!");
		return "redirect:/home";
	}
	
	public Date getFormatedDate(String date) throws ParseException {
		String date2 = date.split(" ")[0];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(date2);
		Date sql = new Date(parsed.getTime());
		return sql;
	}
	
}
