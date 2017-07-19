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
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.entities.OperationType;
import com.unimer.cotizaciones.entities.User;
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
	public String addOperation(HttpServletRequest request,@ModelAttribute(name ="operation") Operation operation, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addOperation in OperationController -- PARAMS: " + operation.toString());
		OperationType operationType = operationTypeService.findById(operation.getOperationType().getIdOperationType());
		operation.setOperationType(operationType);
		operationService.addOperation(operation,userSession.getIdUser());
		model.addAttribute("operations", operationService.listAllOperation());
		return "operation :: #operationRow";
	}

	@GetMapping("/admin/addoperation")
	public String getOperation(){
		return "redirect:/admin/operation";
	}
}
