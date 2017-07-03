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

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.DepartureService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;


@Controller
@SessionAttributes({"userSession"})
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
	public ModelAndView departure(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession){
		Country cntry = countryService.findById(userSession.getIdCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("departure");
		modelAndView.addObject("departures", departureService.listAllDeparture());
		modelAndView.addObject("currencyTypes", cntry.getCurrencyType());
		LOG.info("METHOD: addDeparture in DepartureController -- PARAMS: " +cntry.getCurrencyType().toString());
		return modelAndView;
	}
	

	@PostMapping("/admin/adddeparture")
	public String addDeparture(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name ="departure") Departure departure, Model model) {
		LOG.info("METHOD: addDeparture in DepartureController -- PARAMS: " + departure.toString());
		Country cntry = countryService.findById(userSession.getIdCountry());
		CurrencyType currencyType = currencyTypeService.getCurrencyType(departure.getCurrencyType().getIdCurrencyType());
		departure.setCountry(cntry);
		departure.setCurrencyType(currencyType);
		departureService.addDeparture(departure,userSession.getId());
		model.addAttribute("departures", departureService.listAllDeparture());
		return "departure :: #departureRow";
	}

	@GetMapping("/admin/adddeparture")
	public String getDeparture(){
		return "redirect:/admin/departure";
	}

}
