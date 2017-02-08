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

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.services.ConsecutiveService;

@Controller
public class ConsecutiveController {

	@Autowired
	@Qualifier("consecutiveServiceImpl")

	private ConsecutiveService consecutiveService;

	private static final Log LOG = LogFactory.getLog(ConsecutiveController.class);

	@GetMapping("/admin/consecutive")
	public ModelAndView consecutive() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("consecutive");
		modelAndView.addObject("consecutives", consecutiveService.listAllConsecutives());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}

	@PostMapping("/admin/addconsecutive")
	public ModelAndView addConsecutive(@ModelAttribute(name = "consecutive") Consecutive consecutive, Model model) {
		LOG.info("METHOD: addConsecutive -- PARAMS: " + consecutive.toString());
		consecutiveService.addConsecutive(consecutive);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("consecutive");
		modelAndView.addObject("consecutives", consecutiveService.listAllConsecutives());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}

	@GetMapping("/admin/addconsecutive")
	public String getConsecutive() {
		return "redirect:/admin/consecutive";
	}

	@GetMapping("/admin/chargeconsecutive")
	public ModelAndView chargeConsecutive(String type, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("consecutive");
		modelAndView.addObject("updateConsecutive", consecutiveService.findByType(type));
		modelAndView.addObject("consecutives", consecutiveService.listAllConsecutives());
		return modelAndView;
	}

}
