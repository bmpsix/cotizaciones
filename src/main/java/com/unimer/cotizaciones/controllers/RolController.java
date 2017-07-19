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
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.RolService;

@Controller
public class RolController {

	
	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;

	private static final Log LOG = LogFactory.getLog(RolController.class);
	
	
	@GetMapping("/admin/role")
	public ModelAndView role(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role");
		modelAndView.addObject("roles", rolService.listAllRol());
		return modelAndView;
	}
	
	@PostMapping("/admin/addrole")
	public String addRole(HttpServletRequest request,@ModelAttribute(name = "role") Rol rol, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addRol in RolController -- PARAMS: " + rol.toString());
		rolService.addRol(rol,userSession.getIdUser());
		model.addAttribute("roles", rolService.listAllRol());
		return "role :: #roleRow";
	}
	
	@GetMapping("/admin/addrole")
	public String getRol(){
		return "redirect:/admin/role";
	}
	
}
