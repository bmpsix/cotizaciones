package com.unimer.cotizaciones.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.AssessmentShared;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.HeadUserToUser;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.AssessmentSharedService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.HeadUserToUserService;
import com.unimer.cotizaciones.services.SaClientService;
import com.unimer.cotizaciones.services.SettingsService;
import com.unimer.cotizaciones.services.StatusService;
import com.unimer.cotizaciones.services.UserService;

@Controller
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
	
	@Autowired
	@Qualifier("settingsServiceImpl")
	private SettingsService settingsService;
	
	private static final Log LOG = LogFactory.getLog(AssessmentController.class);
	
	
	@GetMapping("/assessment")
	public ModelAndView assessmentIndex(HttpServletRequest request){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Settings sttings = settingsService.findSettingByCountry(userSession.getCountry());
		CurrencyExchange currencyExchange = currencyExchangeService.findByCountryAndCurrencyType(userSession.getCountry(), sttings.getCurrencyTypeInternational());
		LOG.info("USUARIO EN SESION: "+userSession.toString());
		ModelAndView modelAndView = new ModelAndView();
		List<User> listUsers = userService.listAllUser();
		HeadUserToUser headUserToUser = headUserToUserService.findByUser(userSession);
		if(headUserToUser!=null)listUsers.remove(headUserToUser.getHeadUser());
		listUsers.remove(userSession);
		modelAndView.setViewName("projects");
		if(userSession.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) modelAndView.addObject("projects", assessmentService.listAllAssessmentToHeadUser(userSession));
		else if(userSession.getRol().getDetail().toUpperCase().equals("ADMIN") || userSession.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR"))  modelAndView.addObject("projects", assessmentService.listAllAssessmentByUserCountry(userSession));
		else modelAndView.addObject("projects", assessmentService.listAllByUserAssign(userSession));
		modelAndView.addObject("saClients", saClientService.listAllSaClient());
		modelAndView.addObject("status", statusServiceImpl.listAllStatus());
		modelAndView.addObject("currencyExchange",currencyExchange.getSell());
		modelAndView.addObject("international",sttings.getCurrencyTypeInternational().getSymbol());
		modelAndView.addObject("favorite",sttings.getCurrencyTypeFavorite().getSymbol());
		modelAndView.addObject("users", listUsers);
		modelAndView.addObject("countries", countryService.listAllCountries());
		modelAndView.addObject("role", userSession.getRol().getDetail().toUpperCase());
		modelAndView.addObject("shareds", assessmentSharedService.listAllByUser(userSession));
		modelAndView.addObject("sharedWithMe", assessmentSharedService.listAllByUserShared(userSession));
		LOG.info("ROLE DEL USUARIO EN SESION: "+userSession.getRol().getDetail().toUpperCase());
		return modelAndView;
		
	}
	
	
	
	
	
	@PostMapping("/admin/addassessment")
	public String addAssessment(HttpServletRequest request,
			@RequestParam("idAssessment") int idAssessment,
			@RequestParam("creationDate") Date creationDate,
			@RequestParam("detail") String detail,
			@RequestParam("idCurrencyExchange") double currencyExchange,
			@RequestParam("idSaClient") int idSaClient,
			@RequestParam("idStatus") int idStatus) {
		
		try{
			HttpSession session = request.getSession();
			User userSession =  (User) session.getAttribute("userSession");
			Assessment assessment = new Assessment();
			assessment.setIdAssessment(idAssessment);
			assessment.setCreationDate(creationDate);
			assessment.setCurrencyExchange(currencyExchange);
			assessment.setDetail(detail);
			assessment.setSaClient(saClientService.findById(idSaClient));
			assessment.setStatus(statusServiceImpl.findById(idStatus));
			assessment.setUser(userService.findById(userSession.getIdUser()));
			assessment.setUserAssigned(userService.findById(userSession.getIdUser()));
			LOG.info(assessment.toString());
			assessmentService.addAssessment(assessment, userSession.getIdUser());
			
			return "projects";
			
		}catch(Exception ex){
			return ex.toString();
			
		}
		
	}
	
	@PostMapping("/assessment/addassessmentshared")
	public String addAssessmentShared(	HttpServletRequest request,
										@RequestParam("idUser") int idUser,
										@RequestParam("idAssessmentToShared") int idAssessmentToShared,
										Model model) {
		
		try{
			HttpSession session = request.getSession();
			User userSession =  (User) session.getAttribute("userSession");
			Assessment assessment = assessmentService.findById(idAssessmentToShared);
			User userShared = userService.findById(idUser);
			AssessmentShared assessmentShared = new AssessmentShared(userShared,assessment,userSession);
			if(assessmentSharedService.findByUserAndUserSharedAndAssessment(userSession, userShared, assessment)==null) assessmentSharedService.addAssessmentShared(assessmentShared, userSession.getIdUser());
			else model.addAttribute("msg",1);
			LOG.info(assessmentShared.toString());
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userSession));
			model.addAttribute("role", userSession.getRol().getDetail().toUpperCase());
			return "projects :: #tbodyShared";
			
		}catch(Exception ex){
			return "projects :: #tbodyShared";
			
		}
		
	}
	
	
	@PostMapping("/assessment/deleteassessmentshared")
	public String deleteAssessmentShared(HttpServletRequest request,@RequestParam("idAssessmentShared") int idAssessmentShared,Model model) {
		
		try{
			HttpSession session = request.getSession();
			User userSession =  (User) session.getAttribute("userSession");
			AssessmentShared assessmentShared = assessmentSharedService.findById(idAssessmentShared);
			LOG.info(assessmentShared.toString());
			if(assessmentSharedService.countProposalToAssessmentSharedByUserShared(assessmentShared)==0) assessmentSharedService.delete(assessmentShared);
			else  model.addAttribute("msg",2);
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userSession));
			model.addAttribute("role", userSession.getRol().getDetail().toUpperCase());
			return "projects :: #tbodyShared";
			
		}catch(Exception ex){
			return "projects :: #tbodyShared";	
		}
		
	}
	
	
	@PostMapping("/assessment/assign")
	public String assessmentAssign(HttpServletRequest request,@RequestParam("idAssessmentToAssign") int idAssessmentToAssign,@RequestParam("idUserAssign") int idUserAssign,Model model) {
			HttpSession session = request.getSession();
			User userSession =  (User) session.getAttribute("userSession");
			Assessment assessment = assessmentService.findById(idAssessmentToAssign);
			LOG.info("METHOD assessmentAssign in AssessmentController assessmento assigned: "+assessment.toString());
			User userAssign = userService.findById(idUserAssign);
			LOG.info("METHOD userAssign in AssessmentController assessmento assigned: "+userAssign.toString());
			AssessmentShared assessmentShared = assessmentSharedService.findByUserAndUserSharedAndAssessment(userSession, userAssign, assessment);
			assessment.setUserAssigned(userAssign);
			if(assessmentShared!=null)assessmentSharedService.delete(assessmentShared);
			assessmentService.addAssessment(assessment, userSession.getIdUser());
			assessmentSharedService.updateAssignedShared(assessment,userAssign);
			model.addAttribute("shareds", assessmentSharedService.listAllByUser(userSession));
			model.addAttribute("role", userSession.getRol().getDetail().toUpperCase());
			if(userSession.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) model.addAttribute("projects", assessmentService.listAllAssessmentToHeadUser(userSession));
			else if(userSession.getRol().getDetail().toUpperCase().equals("ADMIN") || userSession.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR")) model.addAttribute("projects", assessmentService.listAllAssessmentByUserCountry(userSession));
			else model.addAttribute("projects", assessmentService.listAllByUserAssign(userSession));
			return "projects";
			
	}
	
	@PostMapping("/assessment/proposal")
	public String assessmentToProposal(HttpServletRequest request,@RequestParam("idAssessment") int idAssessment) {
			HttpSession session = request.getSession();
			Assessment assessment = assessmentService.findById(idAssessment);
			session.setAttribute("assessment",assessment);
			session.setAttribute("proposedHeader", null);
			LOG.info("METHOD assessmentToProposal in AssessmentController  /assessment/proposal : "+assessment.toString());
			return "redirect:/admin/proposal";
			
	}
}
