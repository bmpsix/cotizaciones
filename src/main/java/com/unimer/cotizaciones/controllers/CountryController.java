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

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.TraceResponseService;



@Controller
public class CountryController {

	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	@GetMapping("/admin/country")
	public ModelAndView country() throws UnknownHostException{
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de paises",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("country");
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("consecutive", countryService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addcountry")
	public ModelAndView addCountry(@ModelAttribute(name = "country") Country country, Model model) throws UnknownHostException {
		LOG.info("METHOD: addCountry in CountryController -- PARAMS: " + country.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		countryService.IP(ip);
		countryService.addCountry(country);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("country");
		 modelAndView.addObject("countries", countryService.listAllCountries());
		 modelAndView.addObject("consecutive", countryService.getConsecutive());
		 modelAndView.addObject("updateCountry", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addcountry")
	public String getCountry(){
		return "redirect:/admin/country";
	}
	
	@GetMapping("/admin/chargecountry")
	public ModelAndView chargeCountry(String idCountry, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("country");
			modelAndView.addObject("countries", countryService.listAllCountries());
			modelAndView.addObject("consecutive", countryService.getConsecutive());
			modelAndView.addObject("updateCountry",countryService.findById(idCountry));

		return modelAndView;
	}
	
	

	
}

