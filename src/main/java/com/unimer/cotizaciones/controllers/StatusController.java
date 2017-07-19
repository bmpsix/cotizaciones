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
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.StatusService;

@Controller
public class StatusController {

	@Autowired
	@Qualifier("statusServiceImpl")
	private StatusService statusService;

	
	private static final Log LOG = LogFactory.getLog(StatusController.class);

	
	@GetMapping("/admin/status")
	public ModelAndView status() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("status");
		modelAndView.addObject("allStatus", statusService.listAllStatus());
		return modelAndView;
	}

	@PostMapping("/admin/addstatus")
	public String addStatus(HttpServletRequest request,@ModelAttribute(name = "status") Status status,Model model){
		LOG.info("METHOD: addStatus in StatusController -- PARAMS: " + status.toString());
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		statusService.addStatus(status,userSession.getIdUser());
		model.addAttribute("allStatus", statusService.listAllStatus());
		return "status :: #statusRow";
	}

	@GetMapping("/admin/addstatus")
	public String getStatus() {
		return "redirect:/admin/status";
	}
}
