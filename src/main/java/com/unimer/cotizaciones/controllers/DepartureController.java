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
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.DepartureService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;


@Controller
public class DepartureController {

	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;

	@Autowired
	@Qualifier("departureServiceImpl")
	private DepartureService departureService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;

	private static final Log LOG = LogFactory.getLog(DepartureController.class);

	
	
	@GetMapping("/admin/departure")
	public ModelAndView departure(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("departure");
		modelAndView.addObject("departures", departureService.listAllDeparture());
		modelAndView.addObject("currencyTypes", userSession.getCountry().getCurrencyType());
		LOG.info("METHOD: addDeparture in DepartureController -- PARAMS: " +userSession.getCountry().getCurrencyType().toString());
		return modelAndView;
	}
	

	@PostMapping("/admin/adddeparture")
	public String addDeparture(HttpServletRequest request,@ModelAttribute(name ="departure") Departure departure, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addDeparture in DepartureController -- PARAMS: " + departure.toString());
		CurrencyType currencyType = currencyTypeService.getCurrencyType(departure.getCurrencyType().getIdCurrencyType());
		departure.setCountry(userSession.getCountry());
		departure.setCurrencyType(currencyType);
		departureService.addDeparture(departure,userSession.getIdUser());
		model.addAttribute("departures", departureService.listAllDeparture());
		return "departure :: #departureRow";
	}

	@GetMapping("/admin/adddeparture")
	public String getDeparture(){
		return "redirect:/admin/departure";
	}

}
