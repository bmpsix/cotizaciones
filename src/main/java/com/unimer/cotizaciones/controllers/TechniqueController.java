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
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.TechniqueService;

@Controller
public class TechniqueController {
	
	@Autowired
	@Qualifier("TechniqueServiceImpl")
	private TechniqueService TechniqueService;
	
	
	private static final Log LOG = LogFactory.getLog(TechniqueController.class);
	
	@GetMapping("/admin/technique")
	public ModelAndView Technique(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("technique");
		modelAndView.addObject("Techniques", TechniqueService.listAllTechniques());
		return modelAndView;
	}
	
	@PostMapping("/admin/addtechnique")
	public String addTechnique(HttpServletRequest request,@ModelAttribute(name = "Technique") Technique Technique, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addTechnique in TechniqueController -- PARAMS: " + Technique.toString());
		TechniqueService.addTechnique(Technique,userSession.getIdUser());
		model.addAttribute("Techniques", TechniqueService.listAllTechniques());
		 return "technique :: #techniqueRow";
	}
	
	@GetMapping("/admin/addtechnique")
	public String getTechnique(){
		return "redirect:/admin/technique";
	}
}






