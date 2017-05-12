package com.unimer.cotizaciones.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProposalController {
	
	@GetMapping("/admin/proposal")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView proposal(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
	@GetMapping("/admin/dialogoPartidas")
	public ModelAndView dialogoPartidas(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dialogoPartidas");
		return modelAndView;
		
	}
	
}
