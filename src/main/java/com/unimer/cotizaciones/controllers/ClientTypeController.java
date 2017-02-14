package com.unimer.cotizaciones.controllers;

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

import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.services.ClientTypeService;

@Controller
public class ClientTypeController {

	
	
	@Autowired
	@Qualifier("clientTypeServiceImpl")
	private ClientTypeService clientTypeService;
	
	private static final Log LOG = LogFactory.getLog(ClientTypeController.class);
	
	@GetMapping("/admin/clienttype")
	public ModelAndView clientType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clienttype");
		modelAndView.addObject("clienttypes", clientTypeService.listAllClientType());
		modelAndView.addObject("consecutive", clientTypeService.getConsecutive());
		modelAndView.addObject("updateClientType", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addclienttype")
	public ModelAndView addClientType(@ModelAttribute(name = "clienttype") ClientType clientType, Model model) {
		LOG.info("METHOD: addClientType in ClientTypeController -- PARAMS: " + clientType.toString());
		clientTypeService.addClientType(clientType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("clienttype");
			modelAndView.addObject("clienttypes", clientTypeService.listAllClientType());
			modelAndView.addObject("consecutive", clientTypeService.getConsecutive());
			modelAndView.addObject("updateClientType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addclienttype")
	public String getClientType(){
		return "redirect:/admin/clienttype";
	}
	
	@GetMapping("/admin/updateclienttype")
	public ModelAndView updateRole(String idClientType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("clienttype");
			modelAndView.addObject("clienttypes", clientTypeService.listAllClientType());
			modelAndView.addObject("consecutive", clientTypeService.getConsecutive());
			modelAndView.addObject("updateClientType",clientTypeService.findById(idClientType));

		return modelAndView;
	}
	
	

	
	
}