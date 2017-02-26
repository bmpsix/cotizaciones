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

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.SaClientService;

@Controller
public class AssessmentController {
	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("saClientServiceImpl")
	private SaClientService saClientService;
	
	private static final Log LOG = LogFactory.getLog(AssessmentController.class);
	
	@GetMapping("/admin/assessment")
	public ModelAndView assessment(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("assessment");
		modelAndView.addObject("assessments", assessmentService.listAllAssessment());
		modelAndView.addObject("consecutive", assessmentService.getConsecutive());
		modelAndView.addObject("currencyExchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		return modelAndView;
	}
	
	@PostMapping("/admin/addassessment")
	public ModelAndView addAssessment(@ModelAttribute(name = "assessment") Assessment assessment, Model model) {
		LOG.info("METHOD: addAssessment in AssessmentController -- PARAMS: " + assessment.toString());
		assessmentService.addAssessment(assessment);
		 ModelAndView modelAndView = new ModelAndView();
		 	modelAndView.setViewName("assessment");
		 	modelAndView.addObject("assessments", assessmentService.listAllAssessment());
			modelAndView.addObject("consecutive", assessmentService.getConsecutive());
			modelAndView.addObject("currencyExchanges", currencyExchangeService.listAllCurrencyExchange());
			modelAndView.addObject("saClients", saClientService.listAllSaClient());
			return modelAndView;
	}
	
	@GetMapping("/admin/addassessment")
	public String getClient(){
		return "redirect:/admin/assessment";
	}
	
	@GetMapping("/admin/updateassessment")
	public ModelAndView updateAssessment(String idAssessment, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 	modelAndView.setViewName("clientcontact");
			 	modelAndView.addObject("assessments", assessmentService.listAllAssessment());
				modelAndView.addObject("consecutive", assessmentService.getConsecutive());
				modelAndView.addObject("currencyExchanges", currencyExchangeService.listAllCurrencyExchange());
				modelAndView.addObject("saClients", saClientService.listAllSaClient());
				modelAndView.addObject("updateAssessment",assessmentService.findById(idAssessment));

		return modelAndView;
	}
}
