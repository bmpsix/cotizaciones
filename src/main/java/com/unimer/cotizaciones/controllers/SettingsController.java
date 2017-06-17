package com.unimer.cotizaciones.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.SettingsService;

@Controller
@SessionAttributes({"userSession"})
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin/settings")
	public ModelAndView settings(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession){
		Country cntry = countryService.findById(userSession.getIdCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settings");
		modelAndView.addObject("countrySettings", settingsService.findSettingByCountry(cntry));
		modelAndView.addObject("currencyTypes", cntry.getCurrencyType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addsettings")
	public String addSettings(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute(name = "settings") Settings settings, Model model) {
		LOG.info("METHOD: addSettings in SettingsController -- PARAMS: " + settings.toString());
		Country cntry = countryService.findById(userSession.getIdCountry());
		settings.setCountry(cntry);
		settingsService.addSettings(settings,userSession.getId());
		 return "redirect:/admin/settings";
	}
	
	@GetMapping("/admin/addsettings")
	public String getCountry() {
		return "redirect:/admin/settings";
	}
	
	@GetMapping("/admin/updatesettings")
	public ModelAndView updateSettings(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,int idSettings, Model model) {
		Country cntry = countryService.findById(userSession.getIdCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settings");
		modelAndView.addObject("countrySettings", settingsService.findSettingByCountry(cntry));
		modelAndView.addObject("updateSettings",settingsService.findById(idSettings));
		modelAndView.addObject("currencyTypes", cntry.getCurrencyType());
		return modelAndView;
	}
	
	

	
	
}
