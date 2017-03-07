package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

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


import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class RolController {

	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(RolController.class);
	
	@GetMapping("/admin/role")
	public ModelAndView role() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de Rol",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role");
		modelAndView.addObject("roles", rolService.listAllRol());
		modelAndView.addObject("consecutive", rolService.getConsecutive());
		modelAndView.addObject("updateRole", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addrole")
	public ModelAndView addRole(@ModelAttribute(name = "role") Rol rol, Model model) throws UnknownHostException {
		LOG.info("METHOD: addRol in RolController -- PARAMS: " + rol.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		rolService.IP(ip);
		rolService.addRol(rol);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("role");
		 modelAndView.addObject("roles", rolService.listAllRol());
		 modelAndView.addObject("consecutive", rolService.getConsecutive());
		 modelAndView.addObject("updateRole", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addrole")
	public String getRol(){
		return "redirect:/admin/role";
	}
	
	@GetMapping("/admin/updaterole")
	public ModelAndView updateRole(String idRol, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("role");
			modelAndView.addObject("roles", rolService.listAllRol());
			modelAndView.addObject("consecutive", rolService.getConsecutive());
			modelAndView.addObject("updateRole",rolService.findById(idRol));

		return modelAndView;
	}
	
	

	
}
