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

import com.unimer.cotizaciones.entities.OperationType;
import com.unimer.cotizaciones.services.OperationTypeService;

@Controller
public class OperationTypeController {

	
	@Autowired
	@Qualifier("operationTypeServiceImpl")
	private OperationTypeService operationTypeService;
	
	
	private static final Log LOG = LogFactory.getLog(OperationTypeController.class);
	
	@GetMapping("/admin/operationtype")
	public ModelAndView operationType(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operationtype");
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addoperationtype")
	public String addOperationType(@ModelAttribute(name = "operationType") OperationType operationType, Model model){
		LOG.info("METHOD: addOperationType in OperationTypeController -- PARAMS: " + operationType.toString());
		operationTypeService.addOperationType(operationType);
		 return "redirect:/admin/operationtype";
	}
	
	@GetMapping("/admin/addoperationtype")
	public String getOPT(){
		return "redirect:/admin/operationtype";
	}
	
	@GetMapping("/admin/updateoperationtype")
	public ModelAndView updateOperationType(int idOperationType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("operationtype");
			modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
			modelAndView.addObject("updateOperationType",operationTypeService.findById(idOperationType));

		return modelAndView;
	}
	
	
}
