package com.unimer.cotizaciones.controllers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class CountryByCurrencyTypeController {

	@Autowired
	@Qualifier("countryByCurrencyTypeServiceImpl")
	private CountryByCurrencyTypeService countryByCurrencyTypeService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	
	private static final Log LOG = LogFactory.getLog(CountryByCurrencyTypeController.class);
	
	
	@GetMapping("/admin/countrybycurrencytype")
	public ModelAndView CountryByCurrencyType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("countrybycurrencytype");
		modelAndView.addObject("countryByCurrencyTypes", countryByCurrencyTypeService.listAllCountryByCurrencyType());
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("currencyTypes", currencyTypeService.listAllCurrencyType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addcountrybycurrencytype")
	public String addCountryByCurrencyType(HttpServletRequest request,@RequestParam("idCountry") int idCountry,@RequestParam("idCurrencyType") int idCurrencyType, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addCountryByCurrencyType in CountryByCurrencyTypeController -- PARAMS: idCountry: "+idCountry+" idCurrencyType: "+idCurrencyType);
		countryByCurrencyTypeService.addCountryByCurrencyType(idCountry,idCurrencyType,userSession.getIdUser());
		model.addAttribute("countryByCurrencyTypes", countryByCurrencyTypeService.listAllCountryByCurrencyType());
		 return "countrybycurrencytype :: #countryByCurrencyTypeTbody";
	}
	
	@GetMapping("/admin/addcountrybycurrencytype")
	public String getCountryByCurrencyType(){
		return "redirect:/admin/countrybycurrencytype";
	}
	
	
	@PostMapping("/admin/deletecountrybycurrencytype")
	public String deleteCountryByCurrencyType(@RequestParam("idCountry") int idCountry,@RequestParam("idCurrencyType") int idCurrencyType,Model model) {
		countryService.deleteCountryByCurrencyType(idCountry, idCurrencyType);
		model.addAttribute("countryByCurrencyTypes", countryByCurrencyTypeService.listAllCountryByCurrencyType());
		return "countrybycurrencytype :: #countryByCurrencyTypeTbody";
	
	}
	

}
