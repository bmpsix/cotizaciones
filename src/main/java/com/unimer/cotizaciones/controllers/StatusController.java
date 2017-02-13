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
import com.unimer.cotizaciones.entities.Status;
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
		modelAndView.addObject("consecutive", statusService.getConsecutive());
		modelAndView.addObject("updateStatus", null);
		return modelAndView;
	}

	@PostMapping("/admin/addstatus")
	public ModelAndView addStatus(@ModelAttribute(name = "status") Status status,Model model) {
		LOG.info("METHOD: addStatus in StatusController -- PARAMS: " + status.toString());
		statusService.addStatus(status);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("status");
		modelAndView.addObject("allStatus", statusService.listAllStatus());
		modelAndView.addObject("consecutive", statusService.getConsecutive());
		modelAndView.addObject("updateStatus", null);
		return modelAndView;
	}

	@GetMapping("/admin/addstatus")
	public String getStatus() {
		return "redirect:/admin/status";
	}

	@GetMapping("/admin/updatestatus")
	public ModelAndView updateStatus(String idStatus, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("status");
		modelAndView.addObject("allStatus", statusService.listAllStatus());
		modelAndView.addObject("consecutive", statusService.getConsecutive());
		modelAndView.addObject("updateStatus", statusService.findById(idStatus));
		return modelAndView;
	}

}
