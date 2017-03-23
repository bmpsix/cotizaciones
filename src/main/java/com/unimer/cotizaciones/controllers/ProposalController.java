package com.unimer.cotizaciones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProposalController {
	
	@GetMapping("/admin/proposal")
	public ModelAndView assessment(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
}
