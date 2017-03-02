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

import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.services.ProposalTypeService;




@Controller
public class ProposalTypeController {
	@Autowired
	@Qualifier("proposalTypeImpl")
	private ProposalTypeService proposalTypeService;
	
	private static final Log LOG = LogFactory.getLog(ProposalTypeController.class);
	
	@GetMapping("/admin/proposaltype")
	public ModelAndView proposalType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("proposalType");
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.addObject("consecutive", proposalTypeService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addproposaltype")
	public ModelAndView addProposalType(@ModelAttribute(name = "proposalType") ProposalType proposalType, Model model) {
		LOG.info("METHOD: addProposalType in ProposalTypeController -- PARAMS: " + proposalType.toString());
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