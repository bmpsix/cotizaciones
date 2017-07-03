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
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.StudyCategoryService;

@Controller
@SessionAttributes({"userSession"})
public class StudyCategoryController {

	
	@Autowired
	@Qualifier("studyCategoryImpl")
	private StudyCategoryService studyCategoryService;
	
	private static final Log LOG = LogFactory.getLog(StudyCategoryController.class);
	
	@GetMapping("/admin/studyCategory")
	public ModelAndView studyCategory(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studyCategory");
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		return modelAndView;
	}
	
	@PostMapping("/admin/addStudyCategory")
	public String addStudyCategory(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name = "studyCategory") StudyCategory studyCategory, Model model){
		
		LOG.info("METHOD: addStudyCategory in StudyCategoryController -- PARAMS: " + studyCategory.toString());
		studyCategoryService.addStudyCategory(studyCategory,userSession.getId());
		model.addAttribute("studyCategories", studyCategoryService.listAllStudyCategories());
		return "studyCategory :: #studyCategoryRow";
	}
	
	@GetMapping("/admin/addStudyCategory")
	public String getStudyCategory(){
		return "redirect:/admin/studyCategory";
	}
}
