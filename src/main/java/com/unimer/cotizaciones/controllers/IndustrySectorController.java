package com.unimer.cotizaciones.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class IndustrySectorController {
	@Autowired
	@Qualifier("industrySectorServiceImpl")
	private IndustrySectorService industrySectorService;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(IndustrySectorController.class);
	
	@GetMapping("/admin/industrysector")
	public ModelAndView industrySector() throws UnknownHostException{
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se ingreso a la pagina de tipo de industria",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("industrysector");
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		modelAndView.addObject("consecutive",industrySectorService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addindustrysector")
	public ModelAndView addIndustrySector(@ModelAttribute(name = "industrySector") IndustrySector industrySector, Model model) throws UnknownHostException {
		LOG.info("METHOD: addIndustrySector in IndustrySectorController -- PARAMS: " + industrySector.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		industrySectorService.IP(ip);
		industrySectorService.addIndustrySector(industrySector);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("industrysector");
		 modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		 modelAndView.addObject("consecutive", industrySectorService.getConsecutive());
		 modelAndView.addObject("updateIndustrySector", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addindustrysector")
	public String getIndustrySector(){
		return "redirect:/admin/industrysector";
	}
	
	@GetMapping("/admin/chargeindustrysector")
	public ModelAndView chargeIndustrySector(String idIndustrySector, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("industrysector");
			modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
			modelAndView.addObject("consecutive", industrySectorService.getConsecutive());
			modelAndView.addObject("updateIndustrySector",industrySectorService.findById(idIndustrySector));

		return modelAndView;
	}
	
	
}
