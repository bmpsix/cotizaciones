package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.TechniqueService;

@Controller
@SessionAttributes({"userSession"})
public class TechniqueController {
	@Autowired
	@Qualifier("TechniqueServiceImpl")
	private TechniqueService TechniqueService;
	
	
	private static final Log LOG = LogFactory.getLog(TechniqueController.class);
	
	@GetMapping("/admin/technique")
	public ModelAndView Technique(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Technique");
		modelAndView.addObject("Techniques", TechniqueService.listAllTechniques());
		return modelAndView;
	}
	
	@PostMapping("/admin/addtechnique")
	public String addTechnique(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name = "Technique") Technique Technique, Model model) {
		LOG.info("METHOD: addTechnique in TechniqueController -- PARAMS: " + Technique.toString());
		TechniqueService.addTechnique(Technique,userSession.getId());
		 return "redirect:/admin/technique";
	}
	
	@GetMapping("/admin/addtechnique")
	public String getTechnique(){
		return "redirect:/admin/technique";
	}
	
	@GetMapping("/admin/chargetechnique")
	public ModelAndView chargeTechnique(int idTechnique, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("Technique");
			modelAndView.addObject("Techniques", TechniqueService.listAllTechniques());
			modelAndView.addObject("updateTechnique",TechniqueService.findById(idTechnique));

		return modelAndView;
	}
	
	
}






