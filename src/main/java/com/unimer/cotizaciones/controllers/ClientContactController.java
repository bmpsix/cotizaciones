package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.ClientContactService;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.CountryService;

@Controller
@SessionAttributes({"userSession"})
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
	public String addClientContact(ModelMap modelSession,@ModelAttribute("userSession") User userSession,@ModelAttribute(name = "clientContact") ClientContact clientContact, Model model) {
		LOG.info("METHOD: addClientContact in ClientContactController -- PARAMS: " + clientContact.toString());
		clientContactService.addClientContact(clientContact,userSession.getIdUser());
			return "redirect:/admin/clientcontact";
	}
	
	@GetMapping("/admin/addclientcontact")
	public String getClientContact(){
		return "redirect:/admin/clientcontact";
	}
	
	@GetMapping("/admin/updateclientcontact")
	public ModelAndView updateClientContact(int idClientContact, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 modelAndView.setViewName("clientcontact");
				modelAndView.addObject("countries", countryService.listAllCountries());
				modelAndView.addObject("clients", clientService.findByActiveStatus());
				modelAndView.addObject("clientContacts", clientContactService.listAllClientContact());
			modelAndView.addObject("updateClientContact",clientContactService.findById(idClientContact));

		return modelAndView;
	}
}
