package com.unimer.cotizaciones.controllers;

import java.sql.Date;

import javax.persistence.Convert;

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
import com.unimer.cotizaciones.entities.Target;
import com.unimer.cotizaciones.services.TargetService;

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
		modelAndView.addObject("consecutive", targetService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		
		return modelAndView;
	}
	@PostMapping("/admin/addtarget")
	public  ModelAndView addTarget(@ModelAttribute(name = "target") Target target, Model model)
	{
		LOG.info("METHOD: addTarget in TargetController -- PARAMS:" + model);		
		targetService.addTarget(target);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("target");
		modelAndView.addObject("targets", targetService.listAllTargets());
		modelAndView.addObject("consecutive", targetService.getConsecutive());
		modelAndView.addObject("updateTarget", null);
		return modelAndView;
	}
	
	@GetMapping("/admin/addtarget")
	public String getTarget()
	{
		return "redirect:/admin/target";
	}
	
	@GetMapping("/admind/chargetarget")
	public ModelAndView chargeTarget(String idTarget, Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("target");
		modelAndView.addObject("targets", targetService.listAllTargets());
		modelAndView.addObject("consecutive", targetService.getConsecutive());
		modelAndView.addObject("updateTarget", targetService.findById(idTarget));
		return modelAndView;
		
	}
	
	
}
