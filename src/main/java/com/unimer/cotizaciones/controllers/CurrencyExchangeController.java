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

import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;


@Controller
public class CurrencyExchangeController {
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	//Debo de meter el currency typ service aqui
	
	@Autowired
	@Qualifier("cServiceImpl")
	private CountryService countryService;
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	@GetMapping("/admin/currencyexchange")
	public ModelAndView currencyExchange(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("currencyexchange");
		modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", currencyExchangeService.getConsecutive());
		return modelAndView;
		
	}
	
	@PostMapping("/admin/addcurrencyexchange")
	public ModelAndView addCurrencyExchange(@ModelAttribute(name = "currencyExchange") CurrencyExchange currencyExchange, Model model) {
		LOG.info("METHOD: addCurrencyExchange in CurrencyExchangeController -- PARAMS: " + currencyExchange.toString());
		currencyExchangeService.addCurrencyExchange(currencyExchange);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("currencyexchange");
		 modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		 modelAndView.addObject("consecutive", currencyExchangeService.getConsecutive());
		 return modelAndView;
	}
	
	@GetMapping("/admin/addcurrencyexchange")
	public String getCurrencyExchange(){
		return "redirect:/admin/currencyexchange";
	}
	
	@GetMapping("/admin/chargecurrencyexchange")
	public ModelAndView chargeCurrencyExchange(String idCurrencyExchange, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("country");
			modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
			modelAndView.addObject("consecutive", currencyExchangeService.getConsecutive());
			modelAndView.addObject("updateCurrencyExchange",currencyExchangeService.getCurrencyExchange(idCurrencyExchange));

		return modelAndView;
	}
	
}
