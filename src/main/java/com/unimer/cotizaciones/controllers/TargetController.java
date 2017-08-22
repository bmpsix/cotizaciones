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
import com.unimer.cotizaciones.entities.Target;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.TargetService;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class TargetController {

	@Autowired
	@Qualifier("targetServiceImpl")
	private TargetService targetService;

	private static final Log LOG = LogFactory.getLog(TargetController.class);
	
	
	
	
	@GetMapping("/admin/target")
	public ModelAndView TargetService()
	{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("target");
		modelAndView.addObject("targets", targetService.listAllTargets());
		
		return modelAndView;
	}
	@PostMapping("/admin/addtarget")
	public  String addTarget(HttpServletRequest request,@ModelAttribute(name = "target") Target target, Model model)
	{
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addTarget in TargetController -- PARAMS:" + model);		
		targetService.addTarget(target,userSession.getIdUser());
		return "redirect:/admin/target";
	}
	
	@GetMapping("/admin/addtarget")
	public String getTarget(){
		return "redirect:/admin/target";
	}
	
	@GetMapping("/admin/chargetarget")
	public ModelAndView chargeTarget(int idTarget, Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("target");
		modelAndView.addObject("targets", targetService.listAllTargets());
		modelAndView.addObject("updateTarget", targetService.findById(idTarget));
		return modelAndView;
		
	}
	
	
}
