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

import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CurrencyTypeService;



@Controller
@SessionAttributes({"userSession"})
public class CurrencyTypeController {
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	private static final Log LOG = LogFactory.getLog(CurrencyTypeController.class);
	
	@GetMapping("/admin/currencytype")
	public ModelAndView currencyType(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("currencyType");
		modelAndView.addObject("currencytypes", currencyTypeService.listAllCurrencyType());
		return modelAndView;
	}
	
	@PostMapping("/admin/addcurrencytype")
	public String addCurrencyType(ModelMap modelSession,@ModelAttribute("userSession") User userSession,@ModelAttribute(name = "currencytype") CurrencyType currencyType, Model model) {
		LOG.info("METHOD: addCurrencyType in CurrencyTypeController -- PARAMS: " + currencyType.toString());
		currencyTypeService.addCurrencyType(currencyType,userSession.getIdUser());
		 return "redirect:/admin/currencytype";
	}
	
	@GetMapping("/admin/addcurrencytype")
	public String getCurrencyType() {
		return "redirect:/admin/currencytype";
	}
	
	@GetMapping("/admin/chargecurrencytype")
	public ModelAndView chargeRole(int idCurrencyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("currencyType");
			modelAndView.addObject("currencytypes", currencyTypeService.listAllCurrencyType());
			modelAndView.addObject("updateCurrencyType",currencyTypeService.getCurrencyType(idCurrencyType));

		return modelAndView;
	}

}
