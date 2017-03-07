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

import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.ProposalTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;




@Controller
public class ProposalTypeController {
	@Autowired
	@Qualifier("proposalTypeServiceImpl")
	private ProposalTypeService proposalTypeService;
	
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(ProposalTypeController.class);
	
	@GetMapping("/admin/proposaltype")
	public ModelAndView proposalType() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de tipo de propuesta",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("proposalType");
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.addObject("consecutive", proposalTypeService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addproposaltype")
	public ModelAndView addProposalType(@ModelAttribute(name = "proposalType") ProposalType proposalType, Model model) throws UnknownHostException {
		LOG.info("METHOD: addProposalType in ProposalTypeController -- PARAMS: " + proposalType.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		proposalTypeService.IP(ip);
		proposalTypeService.addProposalType(proposalType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("proposalType");
		 modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		 modelAndView.addObject("consecutive", proposalTypeService.getConsecutive());
		 modelAndView.addObject("updateProposalType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addproposaltype")
	public String getProposalType(){
		return "redirect:/admin/proposaltype";
	}
	
	@GetMapping("/admin/chargeproposaltype")
	public ModelAndView chargeproposalType(String idProposalType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("proposalType");
			modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
			modelAndView.addObject("consecutive", proposalTypeService.getConsecutive());
			modelAndView.addObject("updateProposalType",proposalTypeService.findById(idProposalType));

		return modelAndView;
	}	
	

}