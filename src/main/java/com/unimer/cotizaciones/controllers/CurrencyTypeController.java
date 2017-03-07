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

import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;



@Controller
public class CurrencyTypeController {
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(CurrencyTypeController.class);
	
	@GetMapping("/admin/currencytype")
	public ModelAndView currencyType() throws UnknownHostException{
		Date date = new Date();
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de tipo de moneda",ip,date);
		traceResponseService.addTraceResponse(traceResponse);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("currencyType");
		modelAndView.addObject("currencytypes", currencyTypeService.listAllCurrencyType());
		modelAndView.addObject("consecutive", currencyTypeService.getConsecutive());
		return modelAndView;
	}
	
	@PostMapping("/admin/addcurrencytype")
	public ModelAndView addCurrencyType(@ModelAttribute(name = "currencytype") CurrencyType currencyType, Model model) throws UnknownHostException {
		LOG.info("METHOD: addCurrencyType in CurrencyTypeController -- PARAMS: " + currencyType.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		currencyTypeService.IP(ip);
		currencyTypeService.addCurrencyType(currencyType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("currencyType");
		 modelAndView.addObject("currencytypes", currencyTypeService.listAllCurrencyType());
		 modelAndView.addObject("consecutive", currencyTypeService.getConsecutive());
		 return modelAndView;
	}
	
	@GetMapping("/admin/addcurrencytype")
	public String getCurrencyType(){
		return "redirect:/admin/currencytype";
	}
	
	@GetMapping("/admin/chargecurrencytype")
	public ModelAndView chargeRole(String idCurrencyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("currencyType");
			modelAndView.addObject("currencytypes", currencyTypeService.listAllCurrencyType());
			modelAndView.addObject("consecutive", currencyTypeService.getConsecutive());
			modelAndView.addObject("updateCurrencyType",currencyTypeService.getCurrencyType(idCurrencyType));

		return modelAndView;
	}

}
