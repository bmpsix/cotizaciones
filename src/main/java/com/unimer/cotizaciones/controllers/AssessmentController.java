package com.unimer.cotizaciones.controllers;

import java.util.Date;
import java.util.List;

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
import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.model.UserSession;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.AssessmentSharedService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.HeadUserToUserService;
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
	@Qualifier("headUserToUserServiceImpl")
	private HeadUserToUserService headUserToUserService;
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	
	private static final Log LOG = LogFactory.getLog(AssessmentController.class);
	
	@GetMapping("/assessment")
	public ModelAndView assessmentIndex(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession){
		
		User userEntity = userService.findById(userSession.getId());
		ModelAndView modelAndView = new ModelAndView();
		List<User> listUsers = userService.listAllUser();
		HeadUserToUser headUserToUser = headUserToUserService.findByUser(userEntity);
		if(headUserToUser!=null)listUsers.remove(headUserToUser.getHeadUser());
		listUsers.remove(userEntity);
		modelAndView.setViewName("projects");
		if(userSession.getDetailRol().equals("[ROLE_BOSS_CONTRIBUTOR]")) modelAndView.addObject("projects", assessmentService.listAllAssessmentToHeadUser(userEntity));
		else if(userSession.getDetailRol().equals("[ROLE_ADMIN]") || userSession.getDetailRol().equals("[ROLE_ADMINISTRATOR]"))  modelAndView.addObject("projects", assessmentService.listAllAssessmentByUserCountry(userEntity));
		else modelAndView.addObject("projects", assessmentService.listAllByUserAssign(userEntity));
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		modelAndView.addObject("status", statusServiceImpl.listAllStatus());
		modelAndView.addObject("currencyexchanges", currencyExchangeService.listAllCurrencyExchange());
		modelAndView.addObject("users", listUsers);
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("role", userSession.getDetailRol());
		modelAndView.addObject("email", userSession.getMail());
		modelAndView.addObject("shareds", assessmentSharedService.listAllByUser(userEntity));
		modelAndView.addObject("sharedWithMe", assessmentSharedService.listAllByUserShared(userEntity));
		LOG.info("ROLE DEL USUARIO EN SESION: "+userSession.getDetailRol());
		return modelAndView;
		
	}
	
	
	
	
	
	@PostMapping("/admin/addassessment")
	public String addAssessment(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,
			@RequestParam("idAssessment") int idAssessment,
			@RequestParam("creationDate") Date creationDate,
			@RequestParam("detail") String detail,
			@RequestParam("idCurrencyExchange") int idCurrencyExchange,
			@RequestParam("idSaClient") int idSaClient,
			@RequestParam("idStatus") int idStatus) {
		
		try{
			
			Assessment assessment = new Assessment();
			assessment.setIdAssessment(idAssessment);
			assessment.setCreationDate(creationDate);
			assessment.setCurrencyExchange(currencyExchangeService.getCurrencyExchange(idCurrencyExchange));
			assessment.setDetail(detail);
			assessment.setSaClient(saClientService.findById(idSaClient));
			assessment.setStatus(statusServiceImpl.findById(idStatus));
			assessment.setUser(userService.findById(userSession.getId()));
			assessment.setUserAssigned(userService.findById(userSession.getId()));
			LOG.info(assessment.toString());
			assessmentService.addAssessment(assessment, userSession.getId());
			
			return "projects";
			
		}catch(Exception ex){
			return ex.toString();
			
		}
		
	}
	@PostMapping("/assessment/addassessmentshared")
	public String addAssessmentShared(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,
										@RequestParam("idUser") int idUser,
										@RequestParam("idAssessmentToShared") int idAssessmentToShared,
										Model model) {
		
		try{
			
			
			Assessment assessment = assessmentService.findById(idAssessmentToShared);
			User userShared = userService.findById(idUser);
			User session = userService.findById(userSession.getId());
			AssessmentShared assessmentShared = new AssessmentShared(userShared,assessment,session);
			assessmentSharedService.addAssessmentShared(assessmentShared, userSession.getId());
			LOG.info(assessmentShared.toString());
			User userEntity = userService.findById(userSession.getId());
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userEntity));
			model.addAttribute("role", userSession.getDetailRol());
			return "projects :: #sharedRow";
			
		}catch(Exception ex){
			return "projects :: #sharedRow";
			
		}
		
	}
	
	
	@PostMapping("/assessment/deleteassessmentshared")
	public String deleteAssessmentShared(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@RequestParam("idAssessmentShared") int idAssessmentShared,Model model) {
		
		try{
			
			AssessmentShared assessmentShared = assessmentSharedService.findById(idAssessmentShared);
			LOG.info(assessmentShared.toString());
			assessmentSharedService.delete(assessmentShared);
			User userEntity = userService.findById(userSession.getId());
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userEntity));
			model.addAttribute("role", userSession.getDetailRol());
			return "projects :: #sharedRow";
			
		}catch(Exception ex){
			return "projects :: #sharedRow";
			
		}
		
	}
	
	
	@PostMapping("/assessment/assign")
	public String assessmentAssign(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@RequestParam("idAssessmentToAssign") int idAssessmentToAssign,@RequestParam("idUserAssign") int idUserAssign,Model model) {
		
		
			User userEntity = userService.findById(userSession.getId());
			Assessment assessment = assessmentService.findById(idAssessmentToAssign);
			LOG.info("METHOD assessmentAssign in AssessmentController assessmento assigned: "+assessment.toString());
			User userAssign = userService.findById(idUserAssign);
			LOG.info("METHOD userAssign in AssessmentController assessmento assigned: "+userAssign.toString());
			AssessmentShared assessmentShared = assessmentSharedService.findByUserAndUserSharedAndAssessment(userEntity, userAssign, assessment);
			assessment.setUserAssigned(userAssign);
			if(assessmentShared!=null)assessmentSharedService.delete(assessmentShared);
			assessmentService.addAssessment(assessment, userSession.getId());
			assessmentSharedService.updateAssignedShared(assessment,userAssign,userEntity);
			
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userEntity));
			model.addAttribute("role", userSession.getDetailRol());
			if(userSession.getDetailRol().equals("[ROLE_BOSS_CONTRIBUTOR]")) model.addAttribute("projects", assessmentService.listAllAssessmentToHeadUser(userEntity));
			else if(userSession.getDetailRol().equals("[ROLE_ADMIN]") || userSession.getDetailRol().equals("[ROLE_ADMINISTRATOR]"))  model.addAttribute("projects", assessmentService.listAllAssessmentByUserCountry(userEntity));
			else model.addAttribute("projects", assessmentService.listAllByUserAssign(userEntity));
		
			return "projects";
			
		
		
	}
	
	
}
