package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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

import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.TraceResponseService;
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
	

	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(ClientController.class);

	@GetMapping("/admin/user")
	public ModelAndView user() throws UnknownHostException {
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de usuarios",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", userService.getConsecutive());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		modelAndView.addObject("updateUser", null);
		return modelAndView;
	}

	@PostMapping("/admin/adduser")
	public ModelAndView addUser(@ModelAttribute(name = "user") User user, Model model) throws UnknownHostException {
		LOG.info("METHOD: addUser in UserController -- PARAMS: " + user.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		userService.IP(ip);
		userService.addUser(user);
		return user();
	}

	@GetMapping("/admin/adduser")
	public ModelAndView getUser() throws UnknownHostException {
		return user();
	}

	@GetMapping("/admin/updateuser")
	public ModelAndView updateUser(String idUser, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", userService.getConsecutive());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		modelAndView.addObject("updateUser", userService.findById(idUser));
		return modelAndView;
	}
	
	

}
