/*package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;

	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	private static final Log LOG = LogFactory.getLog(ClientController.class);

	@GetMapping("/admin/user")
	public ModelAndView user(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		return modelAndView;
	}

	@PostMapping("/admin/adduser")
	public String addUser(@ModelAttribute(name = "user") User user, Model model) {
		LOG.info("METHOD: addUser in UserController -- PARAMS: " + user.toString());
		userService.addUser(user);
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/adduser")
	public String getUser(){
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/updateuser")
	public ModelAndView updateUser(int idUser, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		modelAndView.addObject("updateUser", userService.findById(idUser));
		return modelAndView;
	}
	
	

}*/
