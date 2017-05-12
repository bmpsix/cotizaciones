package com.unimer.cotizaciones.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import com.unimer.cotizaciones.services.UserService;




@Controller
public class AuthenticationController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	/*En este controller debe de ir toda la logica de logeos, autentificacion y redirecciones*/
	@GetMapping(value={"/","/index","inicio"})
	public String index(){
		return "index";
	}
	
	@GetMapping("/loginsuccess")
	public String loginsuccess(){
		return "proposal";
	}
	
	
	
	
	
	

	
}
