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

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.services.CountryService;


@Controller
public class CountryController {
	
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService   countryService;
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	@GetMapping("/admin/country")
	public ModelAndView country(){
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("country");
		modelAndView.addObject("countries", countryService.listAllCountries());
		return modelAndView;
	}
	
	@PostMapping("/admin/addCountry")
	public ModelAndView addCountry(@ModelAttribute(name = "country") Country country, Model model) {
		LOG.info("METHOD: addCountry in CountryController -- PARAMS: " + country.toString());
		countryService.addCountry(country);
		 ModelAndView mvn = new ModelAndView();
		 mvn.setViewName("country");
		 mvn.addObject("countries", countryService.listAllCountries());
		 return mvn;
		}
	@GetMapping("/admin/addCountry")
	public String getStudyCategory(){
		return "redirect:/admin/country";
	}
}
