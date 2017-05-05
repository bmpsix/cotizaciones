package com.unimer.cotizaciones.controllers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;

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
	public String addCountryByCurrencyType(@RequestParam("idCountry") int idCountry,@RequestParam("idCurrencyType") int idCurrencyType) {
		LOG.info("METHOD: addCountryByCurrencyType in CountryByCurrencyTypeController -- PARAMS: idCountry: "+idCountry+" idCurrencyType: "+idCurrencyType);
		countryByCurrencyTypeService.addCountryByCurrencyType(idCountry,idCurrencyType);
		 return "redirect:/admin/countrybycurrencytype";
	}
	
	@GetMapping("/admin/addcountrybycurrencytype")
	public String getCountryByCurrencyType(){
		return "redirect:/admin/countrybycurrencytype";
	}
	
	
	@GetMapping("/admin/deletecountrybycurrencytype")
	public String deleteCountryByCurrencyType(@RequestParam("idCountry") int idCountry,@RequestParam("idCurrencyType") int idCurrencyType) {
		countryService.deleteCountryByCurrencyType(idCountry, idCurrencyType);
		return "redirect:/admin/countrybycurrencytype";
	}
	

}
