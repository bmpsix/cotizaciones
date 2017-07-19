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
import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.StudyTypeService;

@Controller
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
	public String addStudyType(HttpServletRequest request,@ModelAttribute(name = "StudyType") StudyType StudyType, Model model){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addStudyType in StudyTypeController -- PARAMS: " + StudyType.toString());
		StudyTypeService.addStudyType(StudyType,userSession.getIdUser());
		model.addAttribute("StudyTypes", StudyTypeService.listAllStudyTypes());
		 return "StudyType :: #studyTypeRow";
	}
	
	@GetMapping("/admin/addstudytype")
	public String getStudyType(){
		return "redirect:/admin/studytype";
	}
}