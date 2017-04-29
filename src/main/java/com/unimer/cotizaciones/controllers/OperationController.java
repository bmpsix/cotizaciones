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
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.services.OperationService;
import com.unimer.cotizaciones.services.OperationTypeService;

@Controller
public class OperationController {

	@Autowired
	@Qualifier("operationTypeServiceImpl")
	private OperationTypeService operationTypeService;

	@Autowired
	@Qualifier("operationServiceImpl")
	private OperationService operationService;
	


	private static final Log LOG = LogFactory.getLog(OperationController.class);

	@GetMapping("/admin/operation")
	public ModelAndView operation(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operation");
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		modelAndView.addObject("operations", operationService.listAllOperation());
		return modelAndView;
	}
	

	@PostMapping("/admin/addoperation")
	public String addOperation(@ModelAttribute(name ="operation") Operation operation, Model model) {
		LOG.info("METHOD: addOperation in OperationController -- PARAMS: " + operation.toString());
		operationService.addOperation(operation);
		return "redirect:/admin/operation";
	}

	@GetMapping("/admin/addoperation")
	public String getOperation(){
		return "redirect:/admin/operation";
	}

	@GetMapping("/admin/updateoperation")
	public ModelAndView updateOperation(int idOperation, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operation");;
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("updateOperation", operationService.findById(idOperation));

		return modelAndView;
	}

}
