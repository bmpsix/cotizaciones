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
import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.ProposalTypeService;


@Controller
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
	public String addProposalType(HttpServletRequest request,@ModelAttribute(name = "proposalType") ProposalType proposalType, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addProposalType in ProposalTypeController -- PARAMS: " + proposalType.toString());
		proposalTypeService.addProposalType(proposalType,userSession.getIdUser());
		model.addAttribute("proposalTypes", proposalTypeService.listAllProposalTypes());
		return "proposaltype :: #proposalTypeRow";
	}
	
	@GetMapping("/admin/addproposaltype")
	public String getProposalType(){
		return "redirect:/admin/proposaltype";
	}
}