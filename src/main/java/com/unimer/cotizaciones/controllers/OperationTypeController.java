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

import com.unimer.cotizaciones.entities.OperationType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.OperationTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class OperationTypeController {

	
	@Autowired
	@Qualifier("operationTypeServiceImpl")
	private OperationTypeService operationTypeService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	
	
	private static final Log LOG = LogFactory.getLog(OperationTypeController.class);
	
	@GetMapping("/admin/operationtype")
	public ModelAndView operationType() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de Tipo de operaciones",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("operationtype");
		modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		modelAndView.addObject("consecutive", operationTypeService.getConsecutive());
		modelAndView.addObject("updateOperationType", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addoperationtype")
	public ModelAndView addOperationType(@ModelAttribute(name = "operationType") OperationType operationType, Model model) throws UnknownHostException {
		LOG.info("METHOD: addOperationType in OperationTypeController -- PARAMS: " + operationType.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		operationTypeService.IP(ip);
		operationTypeService.addOperationType(operationType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("operationtype");
		 modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
		 modelAndView.addObject("consecutive", operationTypeService.getConsecutive());
		 modelAndView.addObject("updateOperationType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addoperationtype")
	public String getOPT(){
		return "redirect:/admin/operationtype";
	}
	
	@GetMapping("/admin/updateoperationtype")
	public ModelAndView updateOperationType(String idOperationType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("operationtype");
			modelAndView.addObject("operationTypes", operationTypeService.listAllOperationType());
			modelAndView.addObject("consecutive", operationTypeService.getConsecutive());
			modelAndView.addObject("updateOperationType",operationTypeService.findById(idOperationType));

		return modelAndView;
	}
	
	
}
