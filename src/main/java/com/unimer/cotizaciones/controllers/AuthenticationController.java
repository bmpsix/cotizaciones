package com.unimer.cotizaciones.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.unimer.cotizaciones.services.UserService;
import com.unimer.cotizaciones.services.AssessmentService;

@Controller
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
	public String loginCheck(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//sesión de usuario por defecto
		com.unimer.cotizaciones.entities.User userEntity = userService.findByEmail(user.getUsername()); // datos usuario logueado
		if(session.getAttribute("userSession")==null) session.setAttribute("userSession",userEntity);
		if(userEntity!=null) userService.lastLogin(userEntity);
		return  "redirect:/assessment";
	}
	
	
	@GetMapping("/admin/logout")
	public ModelAndView logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ModelAndView(new RedirectView("/admin/country"));
	}
	
	
	
	
	
	

	
}
