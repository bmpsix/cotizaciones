package com.unimer.cotizaciones.controllers;

import java.util.Date;
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
import com.unimer.cotizaciones.entities.ClientContact;
import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.CollectMethodService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.ExecutionTypeService;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.OperationService;
import com.unimer.cotizaciones.services.ProposalService;
import com.unimer.cotizaciones.services.ProposalTypeService;
import com.unimer.cotizaciones.services.SettingsService;
import com.unimer.cotizaciones.services.StatusService;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.StudyTypeService;
import com.unimer.cotizaciones.services.TargetService;
import com.unimer.cotizaciones.services.TechniqueService;
import com.unimer.cotizaciones.services.UserService;
import com.unimer.cotizaciones.services.impl.ClientContactServiceImpl;


@Controller
public class ProposalController {
	
	
	private static final Log LOG = LogFactory.getLog(ProposalController.class);
	
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
	private StudyTypeService studyTypeService;
	
	
	@Autowired
	@Qualifier("industrySectorServiceImpl")
	private IndustrySectorService industrySectorService;
	
	@Autowired
	@Qualifier("TechniqueServiceImpl")
	private TechniqueService techniqueService;
	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	
	@Autowired
	@Qualifier("targetServiceImpl")
	private TargetService targetService;
	
	@Autowired
	@Qualifier("clientContactServiceImpl")
	private ClientContactServiceImpl clientContactService;
	
	@Autowired
	@Qualifier("executionTypeServiceImpl")
	private ExecutionTypeService executionTypeService;
	
	@Autowired
	@Qualifier("proposalServiceImpl")
	private ProposalService proposalService;
	
	@Autowired
	@Qualifier("operationServiceImpl")
	private OperationService operationService;
	
	@Autowired
	@Qualifier("statusServiceImpl")
	private StatusService statusService;
	
	@Autowired
	@Qualifier("proposalTypeServiceImpl")
	private ProposalTypeService proposalTypeService;
	
	
	@Autowired
	@Qualifier("currencyExchangeServiceImpl")
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("currencyTypeServiceImpl")
	private CurrencyTypeService currencyTypeService;
	
	@Autowired
	@Qualifier("settingsServiceImpl")
	private SettingsService settingsService;
	
	
	
	@GetMapping("/admin/proposal")
	public ModelAndView proposal(HttpServletRequest request){
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Settings sttings = settingsService.findSettingByCountry(userSession.getCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries",countryService.listAllCountries());
		modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("studytypes", studyTypeService.listAllStudyTypes());
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		modelAndView.addObject("techniques", techniqueService.orderlistAllTechniques());
		modelAndView.addObject("targets", targetService.listAllTargets());
		if(userSession.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) modelAndView.addObject("assessments", assessmentService.listAllAssessmentToHeadUser(userSession));
		else if(userSession.getRol().getDetail().toUpperCase().equals("ADMIN") || userSession.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR"))  modelAndView.addObject("assessments", assessmentService.listAllAssessmentByUserCountry(userSession));
		else modelAndView.addObject("assessments", assessmentService.listAllByUserAssign(userSession));
		modelAndView.addObject("clientContacts", clientContactService.findByCountry(userSession.getCountry()));
		modelAndView.addObject("executionTypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("autoIncrement", proposalService.autoIncrement());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("statuss", statusService.listAllStatus());
		modelAndView.addObject("settings",sttings);
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
	@GetMapping("/admin/dialogoPartidas")
	public ModelAndView dialogoPartidas(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("dialogoPartidas");
		return modelAndView;
		
	}
	

	@PostMapping("/admin/addproposal")
	public String addProposal(HttpServletRequest request,@RequestParam("idProposal") int idProposal,@RequestParam("proposalName") String proposalName,@RequestParam("idCurrencyType") int idCurrencyType,@RequestParam("endDate") Date endDate,@RequestParam("initialDate") Date initialDate,@RequestParam("observations") String observations,@RequestParam("targetText") String targetText,@RequestParam("idAssessment") int idAssessment,@RequestParam("idClientContact") int idClientContact,@RequestParam("idCollectMethod") int idCollectMethod,@RequestParam("idCountry") int idCountry,@RequestParam("idExecutionType") int idExecutionType,@RequestParam("idIndustrySector") int idIndustrySector,@RequestParam("idOperation") int idOperation,@RequestParam("idProposalType") int idProposalType,@RequestParam("idStatus") int idStatus,@RequestParam("idStudyCategory") int idStudyCategory,@RequestParam("idStudyType") int idStudyType,@RequestParam("idTechnique") int idTechnique,@RequestParam("tracker") String tracker,@RequestParam("projectType") String projectType,Model model) 
	{
		HttpSession session = (HttpSession) request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		CurrencyType currencyType = currencyTypeService.getCurrencyType(idCurrencyType);
		Settings settings = settingsService.findSettingByCountry(userSession.getCountry());
		CurrencyExchange currencyE = currencyExchangeService.findByCountryAndCurrencyType(userSession.getCountry(), settings.getCurrencyTypeInternational());
		Assessment assessment = assessmentService.findById(idAssessment);
		ClientContact clientContact = clientContactService.findById(idClientContact);
		CollectMethod collectMethod = collectMethodService.getCollectMethod(idCollectMethod);
		Country countryProposal = countryService.findById(idCountry);
		ExecutionType executionType = executionTypeService.findById(idExecutionType);
		IndustrySector industrySector = industrySectorService.findById(idIndustrySector);
		Operation operation = operationService.findById(idOperation);
		ProposalType proposalType = proposalTypeService.findById(idProposalType);
		Status status = statusService.findById(idStatus);
		StudyCategory studyCategory = studyCategoryService.findById(idStudyCategory);
		StudyType studyType= studyTypeService.findById(idStudyType);
		Technique technique = techniqueService.findById(idTechnique);		
		User user = userService.findById(userSession.getIdUser());
		java.util.Date date = new Date();
		
		Proposal  proposal = new Proposal(proposalName,date,currencyE.getSell(), endDate,initialDate,  observations,  targetText,  assessment,clientContact,  collectMethod,  countryProposal,  executionType,industrySector,  operation,  proposalType,  status, studyCategory,  studyType,technique, tracker,projectType,user,currencyType);
		if(idProposal!=0) proposal.setIdProposal(idProposal);	
		proposal = proposalService.addProposal(proposal, userSession.getIdUser());
		LOG.info("METHOD: PROPOSAL -- PARAMS: " + proposal.toString());
		session.setAttribute("proposedHeader",proposal);
		Proposal Proposal =  (Proposal) session.getAttribute("proposedHeader");
		LOG.info("METHOD: PROPOSAL -- PARAMS: " + proposal.toString());
		LOG.info("METHOD: PROPOSedHeader -- PARAMS: " + Proposal.toString());
		return "redirect:/admin/proposaldetails";
	}
	
	@GetMapping("/admin/addproposal")
	public String getProposal() {
		return "redirect:/admin/proposaldetails";
	}
	
	
	
	
	
}
