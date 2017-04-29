package com.unimer.cotizaciones.controllers;

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
import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.services.IndustrySectorService;
@Controller
public class IndustrySectorController {
	@Autowired
	@Qualifier("industrySectorServiceImpl")
	private IndustrySectorService industrySectorService;
	

	private static final Log LOG = LogFactory.getLog(IndustrySectorController.class);
	
	@GetMapping("/admin/industrysector")
	public ModelAndView industrySector(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("industrysector");
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		return modelAndView;
	}
	
	@PostMapping("/admin/addindustrysector")
	public String addIndustrySector(@ModelAttribute(name = "industrySector") IndustrySector industrySector, Model model) {
		LOG.info("METHOD: addIndustrySector in IndustrySectorController -- PARAMS: " + industrySector.toString());
		industrySectorService.addIndustrySector(industrySector);
		 return "redirect:/admin/industrysector";
	}
	
	@GetMapping("/admin/addindustrysector")
	public String getIndustrySector(){
		return "redirect:/admin/industrysector";
	}
	
	@GetMapping("/admin/chargeindustrysector")
	public ModelAndView chargeIndustrySector(int idIndustrySector, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("industrysector");
			modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
			modelAndView.addObject("updateIndustrySector",industrySectorService.findById(idIndustrySector));

		return modelAndView;
	}
	
	
}
