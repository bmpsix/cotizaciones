package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;


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
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	@GetMapping("/admin/currencyexchange")
	public ModelAndView currencyExchange() throws UnknownHostException{
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de tipo de divisa",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("currencyexchange");
		modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("types", currencyTypeService.listAllCurrencyType());
		modelAndView.addObject("consecutive", currencyExchangeService.getConsecutive());
		return modelAndView;
		
	}
	
	@PostMapping("/admin/addcurrencyexchange")
	public ModelAndView addCurrencyExchange(@ModelAttribute(name = "currencyExchange") CurrencyExchange currencyExchange, Model model) throws UnknownHostException {
		LOG.info("METHOD: addCurrencyExchange in CurrencyExchangeController -- PARAMS: " + currencyExchange.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		currencyExchangeService.IP(ip);
		currencyExchangeService.addCurrencyExchange(currencyExchange);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("currencyexchange");
		 modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		 modelAndView.addObject("countries", countryService.listAllCountries());
		 modelAndView.addObject("types", currencyTypeService.listAllCurrencyType());
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
			modelAndView.setViewName("currencyexchange");
			modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
			modelAndView.addObject("consecutive", currencyExchangeService.getConsecutive());
			modelAndView.addObject("updateCurrencyExchange",currencyExchangeService.getCurrencyExchange(idCurrencyExchange));
			modelAndView.addObject("countries", countryService.listAllCountries());
			modelAndView.addObject("types", currencyTypeService.listAllCurrencyType());

		return modelAndView;
	}
	
}
