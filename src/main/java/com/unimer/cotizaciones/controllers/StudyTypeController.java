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

import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.services.StudyTypeService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Controller
public class StudyTypeController {

	
	@Autowired
	@Qualifier("StudyTypeServiceImpl")
	private StudyTypeService StudyTypeService;
	

	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	private static final Log LOG = LogFactory.getLog(StudyTypeController.class);
	
	@GetMapping("/admin/studytype")
	public ModelAndView StudyTypeService() throws UnknownHostException{
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		TraceResponse traceResponse = new TraceResponse(null,"test","Se incresó a la página de Tipo de estudio",ip);
		traceResponseService.addTraceResponse(traceResponse);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("StudyType");
		modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
		modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
		modelAndView.addObject("updateConsecutive", null);
		return modelAndView;
	}
	
	@PostMapping("/admin/addstudytype")
	public ModelAndView addStudyType(@ModelAttribute(name = "StudyType") StudyType StudyType, Model model) throws UnknownHostException {
		LOG.info("METHOD: addStudyType in StudyTypeController -- PARAMS: " + StudyType.toString());
		String ip = InetAddress.getLocalHost().getHostAddress();
		StudyTypeService.IP(ip);
		StudyTypeService.addStudyType(StudyType);
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("StudyType");
		 modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
		 modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
		 modelAndView.addObject("updateStudyType", null);
		 return modelAndView;
	}
	
	@GetMapping("/admin/addstudytype")
	public String getStudyType(){
		return "redirect:/admin/studytype";
	}
	
	@GetMapping("/admin/chargestudytype")
	public ModelAndView chargeStudyType(String idStudyType, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("StudyType");
			modelAndView.addObject("StudyTypes", StudyTypeService.listAllStudyTypes());
			modelAndView.addObject("consecutive", StudyTypeService.getConsecutive());
			modelAndView.addObject("updateStudyType",StudyTypeService.findById(idStudyType));

		return modelAndView;
	}
}