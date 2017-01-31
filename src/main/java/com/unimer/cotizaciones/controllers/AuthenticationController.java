package com.unimer.cotizaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
	/*En este controller debe de ir toda la logica de logeos, autentificacion y redirecciones*/
	@GetMapping(value={"/","/index","inicio"})
	public String index(){
		return "index";
	}
	
	@GetMapping("/admin/dashboard")
	public ModelAndView adminDashboard(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dashboard");
		modelAndView.addObject("viewName", "dashboard");
		return modelAndView;
	}
}
