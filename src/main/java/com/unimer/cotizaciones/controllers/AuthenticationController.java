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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.UserService;
import com.unimer.cotizaciones.services.AssessmentService;

@Controller
@SessionAttributes({"userSession"})
public class AuthenticationController {
	
	
	private static final Log LOG = LogFactory.getLog(AuthenticationController.class);
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	
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
		
		UserSession userSession = new UserSession(userEntity.getIdUser(),userEntity.getEmail(), user.getAuthorities().toString(),userEntity.getCountry().getIdCountry(), userEntity.getCountry().getCod());
		model.addAttribute("userSession",userSession);//se asignan los datos a la variable
		
		if(userEntity!=null) userService.lastLogin(userEntity);
		return  "redirect:/assessment";
	}
	
	
	@GetMapping("/admin/logout")
	public ModelAndView logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ModelAndView(new RedirectView("/admin/country"));
	}
	
	
	
	
	
	

	
}
