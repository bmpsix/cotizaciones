package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.UserService;

@Controller
@SessionAttributes({"userSession"})
public class UserController {

	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;

	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	private static final Log LOG = LogFactory.getLog(ClientController.class);

	@GetMapping("/admin/user")
	public ModelAndView user(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		return modelAndView;
	}

	@PostMapping("/admin/adduser")
	public String addUser(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name = "user") User user, Model model) {
		LOG.info("METHOD: addUser in UserController -- PARAMS: " + user.toString());
		userService.addUser(user,userSession.getId());
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/adduser")
	public String getUser(){
		return "redirect:/admin/user";
	}

	@GetMapping("/admin/updateuser")
	public ModelAndView updateUser(int idUser, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.listAllUser());
		modelAndView.addObject("updateUser", userService.findById(idUser));
		return modelAndView;
	}
	
	@GetMapping("/admin/changepassword")
	public String changePassword(){
		return "changePassword";
	}
	
	@PostMapping("/admin/user/changePassword")
	public ModelAndView updatePassword(
			@RequestParam(name="currentPassword") String currentPassword,
			@RequestParam(name="newPassword") String newPassword){
		
		ModelAndView mvn = new ModelAndView();
		mvn.setViewName("changePassword");
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User userData = userService.findByEmail(user.getUsername());
		
		if(new BCryptPasswordEncoder().matches(currentPassword, userData.getPassword())){
		  userService.addUser(userData, userData.getIdUser());
		  LOG.info("se ha cambiado la contrasena");
		  mvn.addObject("success","Se ha cambio la contrasena correctamente");
		  
		}else{
			
		  LOG.info("hubo un error");
		  mvn.addObject("error","Ha ocurrido un error");
		}
		
		return mvn;
	}
	
	
	
	

}
