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

import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.services.StudyCategoryService;

@Controller
public class StudyCategoryController {

	
	@Autowired
	@Qualifier("studyCategoryImpl")
	private StudyCategoryService   studyCategoryService;

	private static final Log LOG = LogFactory.getLog(StudyCategoryController.class);

	@GetMapping("/admin/studyCategory")
	public ModelAndView studyCategory(){
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studyCategory");
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		return modelAndView;
		}
	@PostMapping("/admin/addStudyCategory")
	public ModelAndView addStudyCategory(@ModelAttribute(name = "studyCategory") StudyCategory studyCategory, Model model) {
		LOG.info("METHOD: addStudyCategory in StudyCategoryController -- PARAMS: " + studyCategory.toString());
		studyCategoryService.addStudyCategory(studyCategory);
		 ModelAndView mvn = new ModelAndView();
		 mvn.setViewName("studyCategory");
		 mvn.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		 return mvn;
		}
	@GetMapping("/admin/addStudyCategory")
	public String getStudyCategory(){
		return "redirect:/admin/studyCategory";
	}
}
