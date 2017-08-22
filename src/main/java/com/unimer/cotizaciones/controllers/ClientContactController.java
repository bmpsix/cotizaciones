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
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.ClientContactService;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.CountryService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
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

	private static final Log LOG = LogFactory.getLog(ClientContactController.class);
	
	
	@GetMapping("/admin/clientcontact")
	public ModelAndView clientContact(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clientcontact");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("clients", clientService.findByActiveStatus());
		modelAndView.addObject("clientContacts", clientContactService.listAllClientContact());
		return modelAndView;
	}
	
	@PostMapping("/admin/addclientcontact")
	public String addClientContact(HttpServletRequest request,@ModelAttribute(name = "clientContact") ClientContact clientContact, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addClientContact in ClientContactController -- PARAMS: " + clientContact.toString());
		Country country = countryService.findById(clientContact.getCountry().getIdCountry());
		Client client = clientService.findById(clientContact.getClient().getIdClient());
		clientContact.setClient(client);
		clientContact.setCountry(country);
		clientContactService.addClientContact(clientContact,userSession.getIdUser());
		model.addAttribute("clientContacts",clientContactService.listAllClientContact());
		return "clientcontact :: #clientContactRow";
	}
	
	
	@GetMapping("/admin/addclientcontact")
	public String getClientContact(){
		return "redirect:/admin/clientcontact";
	}
	
}
