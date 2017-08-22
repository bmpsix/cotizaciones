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
import com.unimer.cotizaciones.entities.ClientType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.ClientTypeService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class ClientTypeController {

	
	
	@Autowired
	@Qualifier("clientTypeServiceImpl")
	private ClientTypeService clientTypeService;
	
	
	private static final Log LOG = LogFactory.getLog(ClientTypeController.class);
	
	@GetMapping("/admin/clienttype")
	public ModelAndView clientType() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("clienttype");
		modelAndView.addObject("clienttypes", clientTypeService.listAllClientType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addclienttype")
	public String addClientType(HttpServletRequest request,@ModelAttribute(name = "clienttype") ClientType clientType, Model model){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession"); 
		LOG.info("METHOD: addClientType in ClientTypeController -- PARAMS: " + clientType.toString());
		 clientTypeService.addClientType(clientType,userSession.getIdUser());
		 model.addAttribute("clienttypes",clientTypeService.listAllClientType());
		 return "clienttype :: #clientTypeRow";
	}
	
	@GetMapping("/admin/addclienttype")
	public String getClientType(){
		return "redirect:/admin/clienttype";
	}
	
	
}
