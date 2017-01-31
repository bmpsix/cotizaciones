package com.unimer.cotizaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
	
	@GetMapping("/admin/client")
	public ModelAndView client(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("client");
		modelAndView.addObject("viewName", "client");
		return modelAndView;
	}
}
