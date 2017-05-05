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
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.services.DepartureService;
import com.unimer.cotizaciones.services.DepartureTypeService;

@Controller
public class DepartureController {

	@Autowired
	@Qualifier("departureTypeServiceImpl")
	private DepartureTypeService departureTypeService;

	@Autowired
	@Qualifier("departureServiceImpl")
	private DepartureService departureService;
	


	private static final Log LOG = LogFactory.getLog(DepartureController.class);

	@GetMapping("/admin/departure")
	public ModelAndView departure(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("departure");
		modelAndView.addObject("departureTypes", departureTypeService.listAllDepartureType());
		modelAndView.addObject("departures", departureService.listAllDeparture());
		return modelAndView;
	}
	

	@PostMapping("/admin/adddeparture")
	public String addDeparture(@ModelAttribute(name ="departure") Departure departure, Model model) {
		LOG.info("METHOD: addDeparture in DepartureController -- PARAMS: " + departure.toString());
		departureService.addDeparture(departure);
		return "redirect:/admin/departure";
	}

	@GetMapping("/admin/adddeparture")
	public String getDeparture(){
		return "redirect:/admin/departure";
	}

	@GetMapping("/admin/updatedeparture")
	public ModelAndView updateDeparture(int idDeparture, Model model) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("departure");;
		modelAndView.addObject("departureTypes", departureTypeService.listAllDepartureType());
		modelAndView.addObject("departures", departureService.listAllDeparture());
		modelAndView.addObject("updateDeparture", departureService.findById(idDeparture));

		return modelAndView;
	}

}