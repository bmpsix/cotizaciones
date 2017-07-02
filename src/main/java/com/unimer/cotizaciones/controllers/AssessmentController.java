package com.unimer.cotizaciones.controllers;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.AssessmentSharedService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.SaClientService;
import com.unimer.cotizaciones.services.StatusService;
import com.unimer.cotizaciones.services.UserService;

@Controller
@SessionAttributes({"userSession"})
public class AssessmentController {
	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	@Autowired
	@Qualifier("assessmentSharedServiceImpl")
	private AssessmentSharedService assessmentSharedService;
	
	@Autowired
	@Qualifier("statusServiceImpl")
	private StatusService statusServiceImpl;
	
	@Autowired
	@Qualifier("countryServiceImpl")
	private CountryService countryService;
	
	@Autowired
	@Qualifier("saClientServiceImpl")
	private SaClientService saClientService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	private static final Log LOG = LogFactory.getLog(AssessmentController.class);
	
	@GetMapping("/assessment")
	public ModelAndView assessmentIndex(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession){
		
		/*Country cntry = countryService.findById(userSession.getIdCountry());*/
		
		com.unimer.cotizaciones.entities.User userEntity = userService.findById(userSession.getId());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("projects");
		modelAndView.addObject("projects", assessmentService.listAllByUser(userEntity));
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		modelAndView.addObject("status", statusServiceImpl.listAllStatus());
		modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("user", userService.findByEmail(userEntity.getEmail()));
		return modelAndView;
		
	}
	
	@RequestMapping(value="/admin/addassessmentshared", method=  RequestMethod.POST)
	@ResponseBody
	public String addAssessmentShared(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute("assessmentShared") AssessmentShared assessmentShared) {
		
		try{
			LOG.info(assessmentShared.toString());
			assessmentSharedService.addAssessmentShared(assessmentShared, userSession.getId());
			return "true";
			
		}catch(Exception ex){
			return null;
			
		}
		
	}
	
	
	@RequestMapping(value="/admin/deleteassessmentshared", method=  RequestMethod.POST)
	@ResponseBody
	public String deleteAssessmentShared(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute("assessmentShared") AssessmentShared assessmentShared) {
		
		try{
			LOG.info(assessmentShared.toString());
			assessmentSharedService.delete(assessmentShared);
			return "true";
			
		}catch(Exception ex){
			return null;
			
		}
		
	}
	
	
	
	@RequestMapping(value="/admin/addassessment", method=  RequestMethod.POST)
	@ResponseBody
	public String addAssessment(@RequestParam("idAssessment") int idAssessment,
			@RequestParam("creationDate") Date creationDate,
			@RequestParam("detail") String detail,
			@RequestParam("idCurrencyExchange") int idCurrencyExchange,
			@RequestParam("idSaClient") int idSaClient,
			@RequestParam("idStatus") int idStatus,
			@RequestParam("idUser") int idUser) {
		
		try{
			Assessment assessment = new Assessment();
			assessment.setIdAssessment(idAssessment);
			assessment.setCreationDate(creationDate);
			assessment.setCurrencyExchange(currencyExchangeService.getCurrencyExchange(idCurrencyExchange));
			assessment.setDetail(detail);
			assessment.setSaClient(saClientService.findById(idSaClient));
			assessment.setStatus(statusServiceImpl.findById(idStatus));
			assessment.setUser(userService.findById(idUser));
			
			LOG.info(assessment.toString());
			assessmentService.addAssessment(assessment, idUser);
			return "true";
			
		}catch(Exception ex){
			return null;
			
		}
		
	}
	
	
	@GetMapping("/admin/updateassesssment")
	public ModelAndView updateAssessment(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,int idAssessment, Model model) {
		Country cntry = countryService.findById(userSession.getIdCountry());
			ModelAndView modelAndView = new ModelAndView();
			 	modelAndView.setViewName("assessment");
			 	modelAndView.addObject("assessments", assessmentService.listAllAssessment());
			 	modelAndView.addObject("currencyTypes", cntry.getCurrencyType());
				modelAndView.addObject("saClients", saClientService.listAllSaClient());
				modelAndView.addObject("updateAssessment",assessmentService.findById(idAssessment));

		return modelAndView;
	}
}
