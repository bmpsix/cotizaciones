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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.SaClient;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.SaClientService;
import com.unimer.cotizaciones.services.UserService;

@Controller
@SessionAttributes({"userSession"})
public class AssessmentController {
	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("saClientServiceImpl")
	private SaClientService saClientService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userServiceImpl;
	
	private static final Log LOG = LogFactory.getLog(AssessmentController.class);
	
	@GetMapping("/admin/assessment")
	public ModelAndView assessment(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("assessment");
		modelAndView.addObject("assessments", assessmentService.listAllAssessment());
		modelAndView.addObject("currencyExchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		modelAndView.addObject("users", userServiceImpl.listAllUser());
		return modelAndView;
		
	}
	
	
	@PostMapping("/admin/addassessment")
	public String addAssessment(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@RequestParam("idAssessment") int idAssessment,@RequestParam("detail") String detail, @RequestParam("idCurrencyExchange") int idCurrencyExchange,@RequestParam("idSaClient") int idSaClient,@RequestParam("idUser") int idUser) {
		LOG.info("METHOD: addAssessment in AssessmentController -- PARAMS: detail: "+detail+" idCurrencyExchange: "+idCurrencyExchange+" saClient: "+idSaClient+" idUser: "+idUser );
		CurrencyExchange currencyExchange = new CurrencyExchange();
		currencyExchange = currencyExchangeService.getCurrencyExchange(idCurrencyExchange);
		User user = new User();
		user=userServiceImpl.findById(idUser);
		SaClient saClient = new SaClient();
		saClient = saClientService.findById(idSaClient);
		Assessment assessment = new Assessment();
		assessment.setIdAssessment(idAssessment);
		assessment.setCurrencyExchange(currencyExchange);
		assessment.setDetail(detail);
		assessment.setSaClient(saClient);
		assessment.setUser(user);
		assessmentService.addAssessment(assessment,userSession.getId());
		return "redirect:/admin/assessment";
	}
	
	@GetMapping("/admin/addassessment")
	public String getClient(){
		return "redirect:/admin/assessment";
	}
	
	@GetMapping("/admin/updateassesssment")
	public ModelAndView updateAssessment(int idAssessment, Model model) {
		
			ModelAndView modelAndView = new ModelAndView();
			 	modelAndView.setViewName("assessment");
			 	modelAndView.addObject("assessments", assessmentService.listAllAssessment());
				modelAndView.addObject("currencyExchanges", currencyExchangeService.listAllCurrencyExchange());
				modelAndView.addObject("saClients", saClientService.listAllSaClient());
				modelAndView.addObject("users", userServiceImpl.listAllUser());
				modelAndView.addObject("updateAssessment",assessmentService.findById(idAssessment));

		return modelAndView;
	}
}
