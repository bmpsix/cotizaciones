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

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;


@Controller
public class CurrencyExchangeController {
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	
	@GetMapping("/admin/currencyexchange")
	public ModelAndView currencyExchange(){
	
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("currencyexchange");
		modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("types", currencyTypeService.listAllCurrencyType());
		return modelAndView;
		
	}
	
	@PostMapping("/admin/addcurrencyexchange")
	public String addCurrencyExchange(HttpServletRequest request,@ModelAttribute(name = "currencyExchange") CurrencyExchange currencyExchange, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addCurrencyExchange in CurrencyExchangeController -- PARAMS: " + currencyExchange.toString());
		Country country = countryService.findById(currencyExchange.getCountry().getIdCountry());
		CurrencyType currencyType = currencyTypeService.getCurrencyType(currencyExchange.getCurrencyType().getIdCurrencyType());
		currencyExchange.setCountry(country);
		currencyExchange.setCurrencyType(currencyType);
		currencyExchangeService.addCurrencyExchange(currencyExchange,userSession.getIdUser());
		model.addAttribute("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		return "currencyexchange :: #currencyExchangeRow";
	}
	
	@GetMapping("/admin/addcurrencyexchange")
	public String getCurrencyExchange() {
		return "redirect:/admin/currencyexchange";
	}
}
