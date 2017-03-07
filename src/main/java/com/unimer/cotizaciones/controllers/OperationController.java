package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.OperationService;
import com.unimer.cotizaciones.services.OperationTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class OperationController {

	@Autowired
	@Qualifier("operationTypeServiceImpl")
	private OperationTypeService operationTypeService;

	@Autowired
	@Qualifier("operationServiceImpl")
	private OperationService operationService;
	
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	

	private static final Log LOG = LogFactory.getLog(OperationController.class);

	@GetMapping("/admin/operation")
	public ModelAndView operation() throws UnknownHostException {
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de Operaciones",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operation");
		modelAndView.addObject("consecutive", operationService.getConsecutive());
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("updateOperation", null);
		return modelAndView;
	}

	@PostMapping("/admin/addoperation")
	public ModelAndView addOperation(@ModelAttribute(name = "operation") Operation operation, Model model) throws UnknownHostException {
		LOG.info("METHOD: addOperation in OperationController -- PARAMS: " + operation.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		operationService.IP(ip);
		operationService.addOperation(operation);
		return operation();
	}

	@GetMapping("/admin/addoperation")
	public ModelAndView getOperation() throws UnknownHostException {
		return operation();
	}

	@GetMapping("/admin/updateoperation")
	public ModelAndView updateOperation(String idOperation, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operation");
		modelAndView.addObject("consecutive", operationService.getConsecutive());
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("updateOperation", operationService.findById(idOperation));

		return modelAndView;
	}

}
