package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.ProposalTypeService;


@Controller
@SessionAttributes({"userSession"})
public class ProposalTypeController {
	@Autowired
	@Qualifier("proposalTypeServiceImpl")
	private ProposalTypeService proposalTypeService;
	
	private static final Log LOG = LogFactory.getLog(ProposalTypeController.class);
	
	@GetMapping("/admin/proposaltype")
	public ModelAndView proposalType(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("proposalType");
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		return modelAndView;
	}
	
	@PostMapping("/admin/addproposaltype")
	public String addProposalType(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name = "proposalType") ProposalType proposalType, Model model) {
		LOG.info("METHOD: addProposalType in ProposalTypeController -- PARAMS: " + proposalType.toString());
		proposalTypeService.addProposalType(proposalType,userSession.getId());
		model.addAttribute("proposalTypes", proposalTypeService.listAllProposalTypes());
		return "proposaltype :: #proposalTypeRow";
	}
	
	@GetMapping("/admin/addproposaltype")
	public String getProposalType(){
		return "redirect:/admin/proposaltype";
	}
}