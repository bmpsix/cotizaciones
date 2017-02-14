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
import com.unimer.cotizaciones.entities.SaClient;
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
		modelAndView.addObject("consecutive", saClientService.getConsecutive());
		modelAndView.addObject("updateSaClient", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addsaclient")
	public ModelAndView addSaClient(@ModelAttribute(name = "saClient") SaClient saClient, Model model) {
		LOG.info("METHOD: addRol in RolController -- PARAMS: " + saClient.toString());
		saClientService.addSaClient(saClient);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("saclient");
			modelAndView.addObject("saClients", saClientService.listAllSaClient());
			modelAndView.addObject("consecutive", saClientService.getConsecutive());
			modelAndView.addObject("updateSaClient", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addsaclient")
	public String getRol(){
		return "redirect:/admin/saclient";
	}
	
	@GetMapping("/admin/updatesaclient")
	public ModelAndView updateSaClient(String idSaClient, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("saclient");
				modelAndView.addObject("saClients", saClientService.listAllSaClient());
				modelAndView.addObject("consecutive", saClientService.getConsecutive());
			modelAndView.addObject("updateSaClient",saClientService.findById(idSaClient));

		return modelAndView;
	}
	
	

}