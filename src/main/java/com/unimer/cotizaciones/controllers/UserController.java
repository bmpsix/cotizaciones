package com.unimer.cotizaciones.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.HeadUserToUserService;
import com.unimer.cotizaciones.services.RolService;
import com.unimer.cotizaciones.services.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("rolServiceImpl")
	private RolService rolService;
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("headUserToUserServiceImpl")
	private HeadUserToUserService headUserToUserService;
	

	private static final Log LOG = LogFactory.getLog(ClientController.class);

	
	
	
	@GetMapping("/admin/user")
	public ModelAndView user(HttpServletRequest request){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject("roles", rolService.findByActiveStatus());
		modelAndView.addObject("users", userService.findByCountry(userSession.getCountry()));
		modelAndView.addObject("headUserToUsers", headUserToUserService.findHeadUserToUser());
		return modelAndView;
	}

	@PostMapping("/admin/adduser")
	public String addUser(HttpServletRequest request,@ModelAttribute(name = "user") User user, @RequestParam("idHeadUser") int idHeadUser, Model model) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Rol rol = rolService.findById(user.getRol().getIdRol());
		if (user.getIdUser() != 0) {
			List<HeadUserToUser> headUserToUser = headUserToUserService.findUserByHeadUser(user);
			if (headUserToUser.isEmpty()) {
				
				user.setRol(rol);
				userService.addUser(user, userSession.getIdUser());
				headUserToUserService.addHeadUserToUser(idHeadUser, user);
				model.addAttribute("roles", rolService.findByActiveStatus());
				model.addAttribute("users", userService.findByCountry(userSession.getCountry()));
				model.addAttribute("headUserToUsers", headUserToUserService.findHeadUserToUser());
				return "user :: #userRow";

			}
			else return "user";
		}
		else 
		{
			user.setRol(rol);
			userService.addUser(user, userSession.getIdUser());
			headUserToUserService.addHeadUserToUser(idHeadUser, user);
			model.addAttribute("roles", rolService.findByActiveStatus());
			model.addAttribute("users", userService.findByCountry(userSession.getCountry()));
			model.addAttribute("headUserToUsers", headUserToUserService.findHeadUserToUser());
			return "user :: #userRow";
		}
		
		
			
	}

	@GetMapping("/admin/adduser")
	public String getUser(){
		return "redirect:/admin/user";
	}
	
	@GetMapping("/admin/changepassword")
	public String changePassword(){
		return "changePassword";
	}
	
	@PostMapping("/admin/changepassword")
	public String updatePassword(@RequestParam(name="currentPassword") String currentPassword,@RequestParam(name="newPassword") String newPassword){
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userData = userService.findByEmail(user.getUsername());
		if(new BCryptPasswordEncoder().matches(currentPassword, userData.getPassword())){
		  userData.setPassword(newPassword);
		  userService.addUser(userData, userData.getIdUser());
		  LOG.info("se ha cambiado la contrasena");
		}else{
		  LOG.info("hubo un error");
		}
		return "changepassword";
	}
	
	
	
	

}
