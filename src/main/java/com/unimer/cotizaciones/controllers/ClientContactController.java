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
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.ClientContactService;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class ClientContactController {

	
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("clientContactServiceImpl")
	private ClientContactService clientContactService;
	
	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(ClientContactController.class);
	
	@GetMapping("/admin/clientcontact")
	public ModelAndView clientContact() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingresoó a la página de contacto de cliente",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientcontact");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", clientContactService.getConsecutive());
		modelAndView.addObject("clients", clientService.findByActiveStatus());
		modelAndView.addObject("clientContacts", clientContactService.listAllClientContact());
		modelAndView.addObject("updateClientContact", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addclientcontact")
	public ModelAndView addClientContact(@ModelAttribute(name = "clientContact") ClientContact clientContact, Model model) throws UnknownHostException {
		LOG.info("METHOD: addClientContact in ClientContactController -- PARAMS: " + clientContact.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		clientContactService.IP(ip);
		clientContactService.addClientContact(clientContact);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("clientcontact");
			modelAndView.addObject("countries", countryService.listAllCountries());
			modelAndView.addObject("consecutive", clientContactService.getConsecutive());
			modelAndView.addObject("clients", clientService.findByActiveStatus());
			modelAndView.addObject("clientContacts", clientContactService.listAllClientContact());
			modelAndView.addObject("updateClientContact", null);
			return modelAndView;
	}
	
	@GetMapping("/admin/addclientcontact")
	public ModelAndView getClientContact() throws UnknownHostException{
		return clientContact();
	}
	
	@GetMapping("/admin/updateclientcontact")
	public ModelAndView updateClientContact(String idClientContact, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("clientcontact");
				modelAndView.addObject("countries", countryService.listAllCountries());
				modelAndView.addObject("consecutive", clientContactService.getConsecutive());
				modelAndView.addObject("clients", clientService.findByActiveStatus());
				modelAndView.addObject("clientContacts", clientContactService.listAllClientContact());
			modelAndView.addObject("updateClientContact",clientContactService.findById(idClientContact));

		return modelAndView;
	}
}
