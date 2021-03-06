package com.unimer.cotizaciones.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.ClientTypeService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.SaClientService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class ClientController {
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("saClientServiceImpl")
	private SaClientService saClientService;
	
	@Autowired
	@Qualifier("clientTypeServiceImpl")
	private ClientTypeService clientTypeService;
	
	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;
	
	
	private static final Log LOG = LogFactory.getLog(ClientController.class);
	
	@GetMapping("/admin/client")
	public ModelAndView client(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("client");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("saClients", saClientService.findByActiveStatus());
		modelAndView.addObject("clientTypes", clientTypeService.findByActiveStatus());
		modelAndView.addObject("clients", clientService.listAllClient());
		return modelAndView;
	}
	
	@PostMapping("/admin/addclient")
	public String addClient(HttpServletRequest request,@ModelAttribute(name = "clientT") Client client, Model model){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addClient in ClientController -- PARAMS: " + client.toString());
		SaClient saclient = saClientService.findById(client.getSaClient().getIdSaClient());
		client.setSaClient(saclient);
		Country country =  countryService.findById(client.getCountry().getIdCountry());
		client.setCountry(country);
		ClientType clientType = clientTypeService.findById(client.getClientType().getIdClientType());
		client.setClientType(clientType);
		clientService.addClient(client,userSession.getIdUser());
		model.addAttribute("clients",clientService.listAllClient());
		
		return"client :: #clientRow";
	}
	
	@GetMapping("/admin/addclient")
	public String getClient(){
		return "redirect:/admin/client";
	}
	
}
