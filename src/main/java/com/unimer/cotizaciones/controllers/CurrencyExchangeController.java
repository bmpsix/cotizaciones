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

import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;


@Controller
@SessionAttributes({"userSession"})
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
	public String addCurrencyExchange(ModelMap modelSession,@ModelAttribute("userSession") User userSession,@ModelAttribute(name = "currencyExchange") CurrencyExchange currencyExchange, Model model) {
		LOG.info("METHOD: addCurrencyExchange in CurrencyExchangeController -- PARAMS: " + currencyExchange.toString());
		currencyExchangeService.addCurrencyExchange(currencyExchange,userSession.getIdUser());
		 return "redirect:/admin/currencyexchange";
	}
	
	@GetMapping("/admin/addcurrencyexchange")
	public String getCurrencyExchange() {
		return "redirect:/admin/currencyexchange";
	}
	
	@GetMapping("/admin/chargecurrencyexchange")
	public ModelAndView chargeCurrencyExchange(int idCurrencyExchange, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("currencyexchange");
			modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
			modelAndView.addObject("updateCurrencyExchange",currencyExchangeService.getCurrencyExchange(idCurrencyExchange));
			modelAndView.addObject("countries", countryService.listAllCountries());
			modelAndView.addObject("types", currencyTypeService.listAllCurrencyType());

		return modelAndView;
	}
	
}
