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
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CurrencyTypeService;


@PreAuthorize("hasRole('ROLE_ADMIN')")
@Controller
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
	public String addCurrencyType(HttpServletRequest request,@ModelAttribute(name = "currencytype") CurrencyType currencyType, Model model) {
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		LOG.info("METHOD: addCurrencyType in CurrencyTypeController -- PARAMS: " + currencyType.toString());
		currencyTypeService.addCurrencyType(currencyType,userSession.getIdUser());
		model.addAttribute("currencytypes", currencyTypeService.listAllCurrencyType());
		return "currencytype :: #currencyTypeRow";
	}
	
	@GetMapping("/admin/addcurrencytype")
	public String getCurrencyType() {
		return "redirect:/admin/currencytype";
	}
	
}
