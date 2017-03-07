package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class StudyCategoryController {

	
	@Autowired
	@Qualifier("studyCategoryImpl")
	private StudyCategoryService studyCategoryService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	
	
	private static final Log LOG = LogFactory.getLog(StudyCategoryController.class);
	
	@GetMapping("/admin/studyCategory")
	public ModelAndView studyCategory() throws UnknownHostException{
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de Categoría de estudio",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("studyCategory");
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("consecutive", studyCategoryService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addStudyCategory")
	public ModelAndView addStudyCategory(@ModelAttribute(name = "studyCategory") StudyCategory studyCategory, Model model) throws UnknownHostException {
		LOG.info("METHOD: addStudyCategory in StudyCategoryController -- PARAMS: " + studyCategory.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		studyCategoryService.IP(ip);
		studyCategoryService.addStudyCategory(studyCategory);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("studyCategory");
		 modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		 modelAndView.addObject("consecutive", studyCategoryService.getConsecutive());
		 modelAndView.addObject("updateStudyCategory", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addStudyCategory")
	public String getStudyCategory(){
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
