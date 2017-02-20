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
	@Qualifier("studyTypeImpl")
	private StudyTypeService studyTypeService;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeController.class);
	
	@GetMapping("/admin/studytype")
	public ModelAndView studyType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studyType");
		modelAndView.addObject("studyTypes", studyTypeService.listAllStudyTypes());
		modelAndView.addObject("consecutive", studyTypeService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addstudytype")
	public ModelAndView addStudyType(@ModelAttribute(name = "studyType") StudyType studyType, Model model) {
		LOG.info("METHOD: addStudyType in StudyTypeController -- PARAMS: " + studyType.toString());
		studyTypeService.addStudyType(studyType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("studyType");
		 modelAndView.addObject("studyTypes", studyTypeService.listAllStudyTypes());
		 modelAndView.addObject("consecutive", studyTypeService.getConsecutive());
		 modelAndView.addObject("updateStudyType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addstudytype")
	public String getStudyType(){
		return "redirect:/admin/studyType";
	}
	
	@GetMapping("/admin/chargestudytype")
	public ModelAndView chargestudyType(String idStudyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("studyType");
			modelAndView.addObject("studyTypes", studyTypeService.listAllStudyTypes());
			modelAndView.addObject("consecutive", studyTypeService.getConsecutive());
			modelAndView.addObject("updateStudyType",studyTypeService.findById(idStudyType));

		return modelAndView;
	}
}