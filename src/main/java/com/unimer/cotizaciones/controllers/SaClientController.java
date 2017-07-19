package com.unimer.cotizaciones.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.SaClientService;

@Controller
public class SaClientController {

	@Autowired
	@Qualifier("saClientServiceImpl")
	private SaClientService saClientService;
	
	private static final Log LOG = LogFactory.getLog(SaClientController.class);
	
	
	@GetMapping("/admin/saclient")
	public ModelAndView saClient(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("saclient");
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		return modelAndView;
	}
	
	@PostMapping("/admin/addsaclient")
	public String addSaClient(HttpServletRequest request,@ModelAttribute(name = "saClient") SaClient saClient, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addRol in RolController -- PARAMS: " + saClient.toString());
		saClientService.addSaClient(saClient,userSession.getIdUser());
		model.addAttribute("saClients", saClientService.listAllSaClient());
		 return "saclient :: #saClientRow";
	}
	
	@GetMapping("/admin/addsaclient")
	public String getRol(){
		return "redirect:/admin/saclient";
	}
}
