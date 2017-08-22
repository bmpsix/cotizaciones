package com.unimer.cotizaciones.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CollectMethodService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class CollectMethodController {
	
	@Autowired
	@Qualifier("collectMethodServiceImpl")
	private CollectMethodService collectMethodService;
	
	
	//private static final Log LOG = LogFactory.getLog(CollectMethodController.class);
	
	@GetMapping("/admin/collectmethod")
	public ModelAndView collectMethod() {
		
		ModelAndView mvn = new ModelAndView();
		mvn.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		mvn.setViewName("collectmethod");
		return mvn;
	}
	
	@PostMapping("/admin/addcollectmethod")
	public String addCollectMethod(HttpServletRequest request,@ModelAttribute(name = "collectMethod") CollectMethod collectMethod, Model model){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		collectMethodService.addCollectMethod(collectMethod,userSession.getIdUser()); 
		model.addAttribute("collectmethods", collectMethodService.listAllCollectMethod());
		return "collectmethod :: #collectMethodRow";
	}
	
	@GetMapping("/admin/addcollectmethod")
	public String getCollectMethod(){
		return "redirect:/admin/collectmethod";
	}
}
