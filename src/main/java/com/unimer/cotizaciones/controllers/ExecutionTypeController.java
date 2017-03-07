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
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.ExecutionTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class ExecutionTypeController {

	@Autowired
	@Qualifier("executionTypeServiceImpl")
	private ExecutionTypeService executionTypeService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(ExecutionTypeController.class);

	@GetMapping("/admin/executiontype")
	public ModelAndView executionType() throws UnknownHostException {
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de tipo de ejecucion",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("executiontype");
		modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
		modelAndView.addObject("updateExecutionType", null);
		return modelAndView;
	}

	@PostMapping("/admin/addexecutiontype")
	public ModelAndView addExecutionType(@ModelAttribute(name = "executiontype") ExecutionType executionType,
			Model model) throws UnknownHostException {
		LOG.info("METHOD: addExecutionType in ExecutionTypeController -- PARAMS: " + executionType.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		executionTypeService.IP(ip);
		executionTypeService.addExecutionType(executionType);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("executiontype");
		modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
		modelAndView.addObject("updateExecutionType", null);
		return modelAndView;
	}

	@GetMapping("/admin/addexecutiontype")
	public String getExecutionType() {
		return "redirect:/admin/executiontype";
	}

	@GetMapping("/admin/updateexecutiontype")
	public ModelAndView updateExecutionType(String idExecutionType, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("executiontype");
		modelAndView.addObject("executiontypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("consecutive", executionTypeService.getConsecutive());
		modelAndView.addObject("updateExecutionType", executionTypeService.findById(idExecutionType));

		return modelAndView;
	}

}
