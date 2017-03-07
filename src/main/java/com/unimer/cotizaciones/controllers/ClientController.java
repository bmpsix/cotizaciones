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

import com.unimer.cotizaciones.entities.Client;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.ClientTypeService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.SaClientService;
import com.unimer.cotizaciones.services.TraceResponseService;

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
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(ClientController.class);
	
	@GetMapping("/admin/client")
	public ModelAndView client() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de clientes",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("client");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", clientService.getConsecutive());
		modelAndView.addObject("saClients", saClientService.findByActiveStatus());
		modelAndView.addObject("clientTypes", clientTypeService.findByActiveStatus());
		modelAndView.addObject("clients", clientService.listAllClient());
		modelAndView.addObject("updateClient", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addclient")
	public ModelAndView addClient(@ModelAttribute(name = "client") Client client, Model model) throws UnknownHostException {
		LOG.info("METHOD: addClient in ClientController -- PARAMS: " + client.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		clientService.IP(ip);
		clientService.addClient(client);
			return client();
	}
	
	@GetMapping("/admin/addclient")
	public String getClient(){
		return "redirect:/admin/client";
	}
	
	@GetMapping("/admin/updateclient")
	public ModelAndView updateClient(String idClient, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("client");
				modelAndView.addObject("countries", countryService.listAllCountries());
				modelAndView.addObject("consecutive", clientService.getConsecutive());
				modelAndView.addObject("saClients", saClientService.findByActiveStatus());
				modelAndView.addObject("clientTypes", clientTypeService.findByActiveStatus());
				modelAndView.addObject("clients", clientService.listAllClient());
			modelAndView.addObject("updateClient",clientService.findById(idClient));

		return modelAndView;
	}
	

	
}
