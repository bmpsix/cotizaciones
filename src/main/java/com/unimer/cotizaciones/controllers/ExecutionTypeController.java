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
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.services.ExecutionTypeService;


@Controller
public class ExecutionTypeController {

	@Autowired
	@Qualifier("executionTypeServiceImpl")
	private ExecutionTypeService executionTypeService;
	
private static final Log LOG = LogFactory.getLog(ExecutionTypeController.class);
	
	@GetMapping("/admin/executiontype")
	public ModelAndView executionType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("executiontype");
		modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
		modelAndView.addObject("updateExecutionType", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addexecutiontype")
	public ModelAndView addExecutionType(@ModelAttribute(name = "executiontype") ExecutionType executionType, Model model) {
		LOG.info("METHOD: addExecutionType in ExecutionTypeController -- PARAMS: " + executionType.toString());
		executionTypeService.addExecutionType(executionType);
		 ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("executiontype");
			modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
			modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
			modelAndView.addObject("updateExecutionType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addexecutiontype")
	public String getExecutionType(){
		return "redirect:/admin/executiontype";
	}
	
	@GetMapping("/admin/updateexecutiontype")
	public ModelAndView updateExecutionType(String idExecutionType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("executiontype");
			modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
			modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
			modelAndView.addObject("updateExecutionType",executionTypeService.findById(idExecutionType));

		return modelAndView;
	}
	
	

	
}
