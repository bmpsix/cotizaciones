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

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.StudyCategoryService;

@Controller
public class StudyCategoryController {

	
	@Autowired
	@Qualifier("studyCategoryImpl")
	private StudyCategoryService studyCategoryService;
	
	private static final Log LOG = LogFactory.getLog(StudyCategoryController.class);
	
	@GetMapping("/admin/studyCategory")
	public ModelAndView studyCategory(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studyCategory");
		modelAndView.addObject("studyCategory", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("consecutive", studyCategoryService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addStudyCategory")
	public ModelAndView addStudyCategory(@ModelAttribute(name = "studyCategory") StudyCategory studyCategory, Model model) {
		LOG.info("METHOD: addStudyCategory in StudyCategoryController -- PARAMS: " + studyCategory.toString());
		studyCategoryService.addStudyCategory(studyCategory);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("studyCategory");
		 modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		 modelAndView.addObject("consecutive", studyCategoryService.getConsecutive());
		 modelAndView.addObject("updateStudyCategory", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addStudyCategory")
	public String getRol(){
		return "redirect:/admin/studyCategory";
	}
	
	@GetMapping("/admin/chargestudyCategory")
	public ModelAndView chargestudyCategory(String idStudyCategory, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("studyCategory");
			modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
			modelAndView.addObject("consecutive", studyCategoryService.getConsecutive());
			modelAndView.addObject("updateStudyCategory",studyCategoryService.findById(idStudyCategory));

		return modelAndView;
	}
}
