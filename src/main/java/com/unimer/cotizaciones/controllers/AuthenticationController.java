package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.unimer.cotizaciones.services.UserService;


@Controller
@SessionAttributes({"userSession"})
public class AuthenticationController {
	
	
	private static final Log LOG = LogFactory.getLog(AuthenticationController.class);
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	
	
	@GetMapping("/index")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout",required=false) String logout){
		
		LOG.info("METHOD: showLoginForm() -- PARAMS: error="+error+",logout:"+logout);
		
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		LOG.info("Returning to login view");
		return "index";
	}
	
	/*En este controller debe de ir toda la logica de logeos, autentificacion y redirecciones*/

	
	@GetMapping({"/loginsuccess","/","/css/images/ajax-loader.gif"})
	public String loginCheck(ModelMap model){
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//sesión de usuario por defecto
		com.unimer.cotizaciones.entities.User userEntity = userService.findByEmail(user.getUsername()); // datos usuario logueado
		model.addAttribute("userSession",userEntity);//se asignan los datos a la variable
		return  "redirect:/admin/proposal";
	}
	
	
	
	
	
	

	
}
