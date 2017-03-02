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

import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.services.StudyTypeService;

@Controller
public class StudyTypeController {

	
	@Autowired
	@Qualifier("StudyTypeImpl")
	private StudyTypeService StudyTypeService;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeController.class);
	
	@GetMapping("/admin/StudyType")
	public ModelAndView StudyType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("StudyType");
		modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
		modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addStudyType")
	public ModelAndView addStudyType(@ModelAttribute(name = "StudyType") StudyType StudyType, Model model) {
		LOG.info("METHOD: addStudyType in StudyTypeController -- PARAMS: " + StudyType.toString());
		StudyTypeService.addStudyType(StudyType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("StudyType");
		 modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
		 modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
		 modelAndView.addObject("updateStudyType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addStudyType")
	public String getStudyType(){
		return "redirect:/admin/StudyType";
	}
	
	@GetMapping("/admin/chargeStudyType")
	public ModelAndView chargeStudyType(String idStudyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("StudyType");
			modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
			modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
			modelAndView.addObject("updateStudyType",StudyTypeService.findById(idStudyType));

		return modelAndView;
	}
}