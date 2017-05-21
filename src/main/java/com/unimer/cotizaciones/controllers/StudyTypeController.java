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

import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.StudyTypeService;

@Controller
@SessionAttributes({"userSession"})
public class StudyTypeController {

	
	@Autowired
	@Qualifier("StudyTypeServiceImpl")
	private StudyTypeService StudyTypeService;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeController.class);
	
	@GetMapping("/admin/studytype")
	public ModelAndView StudyTypeService(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("StudyType");
		modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
		return modelAndView;
	}
	
	@PostMapping("/admin/addstudytype")
	public String addStudyType(ModelMap modelSession,@ModelAttribute("userSession") User userSession,@ModelAttribute(name = "StudyType") StudyType StudyType, Model model){
		LOG.info("METHOD: addStudyType in StudyTypeController -- PARAMS: " + StudyType.toString());
		StudyTypeService.addStudyType(StudyType,userSession.getIdUser());
		 return "redirect:/admin/studytype";
	}
	
	@GetMapping("/admin/addstudytype")
	public String getStudyType(){
		return "redirect:/admin/studytype";
	}
	
	@GetMapping("/admin/chargestudytype")
	public ModelAndView chargeStudyType(int idStudyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("StudyType");
			modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
			modelAndView.addObject("updateStudyType",StudyTypeService.findById(idStudyType));

		return modelAndView;
	}
}