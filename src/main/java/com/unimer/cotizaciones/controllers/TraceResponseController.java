package com.unimer.cotizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.services.TraceResponseService;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class TraceResponseController {

	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	@GetMapping("/admin/traceresponse")
	public ModelAndView client(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("traceresponse");
		modelAndView.addObject("traces", traceResponseService.listAllTraceResponse());
		return modelAndView;
	}
}
