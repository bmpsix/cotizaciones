package com.unimer.cotizaciones.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.User;


@Controller
@SessionAttributes({"userSession"})//permite utilizar la misma variable de sesion que se creó en el AuthenticationController
public class ProposalController {
	
	@GetMapping("/admin/proposal")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView proposal(ModelMap model,@ModelAttribute("userSession") User userSession){ // una variable model y modelAttribute que corresponde a la sessionAttribute, el nombre debe ser igual
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userEmail",userSession.getEmail());// La variable puede ser utilizada
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
	@GetMapping("/admin/dialogoPartidas")
	public ModelAndView dialogoPartidas(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dialogoPartidas");
		return modelAndView;
		
	}
	
}
