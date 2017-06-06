package com.unimer.cotizaciones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.CollectMethodService;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.StudyTypeService;
import com.unimer.cotizaciones.services.TechniqueService;


@Controller
@SessionAttributes({"userSession"})
public class ProposalController {
	
	
	
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("collectMethodServiceImpl")
	private CollectMethodService collectMethodService;
	
	@Autowired
	@Qualifier("studyCategoryImpl")
	private StudyCategoryService studyCategoryService;
	
	
	@Autowired
	@Qualifier("StudyTypeServiceImpl")
	private StudyTypeService StudyTypeService;
	
	
	@Autowired
	@Qualifier("industrySectorServiceImpl")
	private IndustrySectorService industrySectorService;
	
	@Autowired
	@Qualifier("TechniqueServiceImpl")
	private TechniqueService techniqueService;
	
	@Autowired
	@Qualifier("countryByCurrencyTypeServiceImpl")
	private CountryByCurrencyTypeService countryByCurrencyTypeService;
	
	
	
	@GetMapping("/admin/proposal")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView proposal(ModelMap model,@ModelAttribute("userSession") UserSession userSession){
		Country cntry = countryService.findById(userSession.getIdCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries",countryService.listAllCountries());
		modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("studytypes", StudyTypeService.listAllStudyTypes());
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		modelAndView.addObject("techniques", techniqueService.orderlistAllTechniques());
		modelAndView.addObject("countryByCurrencyType", cntry.getCurrencyType());
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
	@GetMapping("/admin/dialogoPartidas")
	public ModelAndView dialogoPartidas(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dialogoPartidas");
		return modelAndView;
		
	}
	
	
	
}
