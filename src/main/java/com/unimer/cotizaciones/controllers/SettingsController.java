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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.SettingsService;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
public class SettingsController {

	@Autowired
	@Qualifier("settingsServiceImpl")
	private SettingsService settingsService;
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	private static final Log LOG = LogFactory.getLog(CountryController.class);
	
	
	
	//@PreAuthorize("hasRole('ROLE_CONTRIBUTOR')")
	@GetMapping("/admin/settings")
	public ModelAndView settings(HttpServletRequest request){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Country country = countryService.findById(userSession.getCountry().getIdCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settings");
		modelAndView.addObject("countrySettings", settingsService.findSettingByCountry(country));
		modelAndView.addObject("currencyTypes", country.getCurrencyType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addsettings")
	public String addSettings(HttpServletRequest request,@ModelAttribute(name = "settings") Settings settings, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addSettings in SettingsController -- PARAMS: " + settings.toString());
		CurrencyType favorite = currencyTypeService.getCurrencyType(settings.getCurrencyTypeFavorite().getIdCurrencyType());
		CurrencyType international = currencyTypeService.getCurrencyType(settings.getCurrencyTypeInternational().getIdCurrencyType());
		settings.setCountry(userSession.getCountry());
		settings.setCurrencyTypeFavorite(favorite);
		settings.setCurrencyTypeInternational(international);
		settingsService.addSettings(settings,userSession.getIdUser());
		model.addAttribute("countrySettings", settingsService.findSettingByCountry(userSession.getCountry()));
		 return "settings :: #settingsRow";
	}
	
	@GetMapping("/admin/addsettings")
	public String getCountry() {
		return "redirect:/admin/settings";
	}
}
