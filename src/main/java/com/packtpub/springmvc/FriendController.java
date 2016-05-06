package com.packtpub.springmvc;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class FriendController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping("/addFriend")
	public String addFriend(@RequestParam String friendName, RedirectAttributes redirectAttributes, Model model,
			HttpSession session) {
		String[] tokenList = friendName.split("\\s+");
		System.out.println("valicina " + tokenList.length);
		if (tokenList.length == 2) {
			User foundUser = personService.findUserByName(tokenList[0], tokenList[1]);
			if (foundUser != null && foundUser.getRole().getId() == 2) {
				System.out.println("nasao ga je");
				User me = (User) session.getAttribute("logedUser");
				if (foundUser.getId() != me.getId()) {

					for (User user : me.getStarter_friend()) {
						System.out.println(user.getId() + " " + foundUser.getId());
						if (user.getId() == foundUser.getId() || me.getId() == foundUser.getId()) {
							System.out.println("vec postoji");
							return "redirect:/home";
						}
					}

					me.getStarter_friend().add(foundUser);
					this.personService.updatePerson(me);
					foundUser.getStarter_friend().add(me);
					this.personService.updatePerson(foundUser);

					return "redirect:/home";
				} else {
					System.out.println("ne moz dodati sebe");
					return "redirect:/home";
				}
			} else {
				System.out.println("nije ga nasao");
				return "redirect:/home";
			}
		}
		System.out.println("cant add other peple");
		return "redirect:/home";
	}

	@RequestMapping("/removeFriend/{id}")
	public String removeFriend(@PathVariable("id") int id, HttpSession session) {
		User toBeDeleted = this.personService.findPerson(id);
		User me = (User) session.getAttribute("logedUser");
		for (User user : me.getStarter_friend()) {
			System.out.println(user.getFirstName());
			if (user.getId() == id) {
				me.getStarter_friend().remove(toBeDeleted);
				this.personService.updatePerson(me);
			}
		}
		
		return "redirect:/home";
	}

}
