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
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.entities.ExecutionType;
import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.entities.Operation;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;
import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.Status;
import com.unimer.cotizaciones.entities.StudyCategory;
import com.unimer.cotizaciones.entities.StudyType;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.AssessmentSharedService;
import com.unimer.cotizaciones.services.BillingScenarioService;
import com.unimer.cotizaciones.services.ClientContactService;
import com.unimer.cotizaciones.services.CollectMethodService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.CurrencyExchangeService;
import com.unimer.cotizaciones.services.CurrencyTypeService;
import com.unimer.cotizaciones.services.DepartureService;
import com.unimer.cotizaciones.services.ExecutionTypeService;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.OperationService;
import com.unimer.cotizaciones.services.ProposalDetailsService;
import com.unimer.cotizaciones.services.ProposalService;
import com.unimer.cotizaciones.services.ProposalTypeService;
import com.unimer.cotizaciones.services.SettingsService;
import com.unimer.cotizaciones.services.StatusService;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.StudyTypeService;
import com.unimer.cotizaciones.services.TargetService;
import com.unimer.cotizaciones.services.TechniqueByProposalService;
import com.unimer.cotizaciones.services.TechniqueService;
import com.unimer.cotizaciones.services.UserService;


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
	private ClientContactService clientContactService;
	
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
	
	@Autowired
	@Qualifier("assessmentSharedServiceImpl")
	private AssessmentSharedService assessmentSharedService;
	
	@Autowired
	@Qualifier("departureServiceImpl")
	private DepartureService departureService;
	
	@Autowired
	@Qualifier("proposalDetailsServiceImpl")
	private ProposalDetailsService proposalDetailsService;
	
	@Autowired
	@Qualifier("techniqueByProposalServiceImpl")
	private TechniqueByProposalService techniqueByProposalService;
	
	@Autowired
	@Qualifier("billingScenarioServiceImpl")
	private BillingScenarioService billingScenarioService;
	
	
	
	@GetMapping("/listproposal/newproposal")
	public String newProposal(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.setAttribute("proposedHeader",null);
		return "redirect:/assessment/proposal";
	}
	
	@PostMapping("/assessment/proposal")
	public String addProposal(HttpServletRequest request,@RequestParam("idProposal") int idProposal)
	{
		HttpSession session = request.getSession();
		Proposal proposal = proposalService.findByIdProposal(idProposal);
		session.setAttribute("proposedHeader",proposal);
		LOG.info("METHOD assessmentToProposal in AssessmentController  /assessment/proposal : "+proposal.toString());
		return "redirect:/assessment/proposal";
	}
	
	@GetMapping("/assessment/proposal")
	public ModelAndView proposal(HttpServletRequest request){
		Proposal proposedHeader=null;
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Assessment assessment = (Assessment) session.getAttribute("assessment");
		if(session.getAttribute("proposedHeader")!=null){proposedHeader = (Proposal) session.getAttribute("proposedHeader");}
		Settings sttings = settingsService.findSettingByCountry(userSession.getCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("countries",countryService.listAllCountries());
		modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("studytypes", studyTypeService.listAllStudyTypes());
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		if(proposedHeader!=null)modelAndView.addObject("formatIdProposal", proposalService.formatNumber(proposedHeader.getIdProposal()));
		else modelAndView.addObject("autoIncrement", proposalService.autoIncrement());
		if(proposedHeader==null)modelAndView.addObject("techniques", techniqueService.orderlistAllTechniques());
		modelAndView.addObject("proposalName", assessment.getDetail());
		modelAndView.addObject("saClientProject", assessment.getSaClient());
		modelAndView.addObject("targets", targetService.listAllTargets());
		modelAndView.addObject("clientContacts", clientContactService.findByCountryAndSaClient(userSession.getCountry(), assessment.getSaClient()));
		modelAndView.addObject("executionTypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("statuss", statusService.listAllStatus());
		modelAndView.addObject("settings",sttings);
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.addObject("countryByCurrencyType", userSession.getCountry().getCurrencyType());
		modelAndView.addObject("currencyType",userSession.getCountry().getCurrencyType());
		if(proposedHeader!=null)modelAndView.addObject("exchangeRate", (float) proposedHeader.getCurrencyExchange());
		if(proposedHeader!=null)modelAndView.addObject("techniquesByProposal", techniqueByProposalService.findTechiquesByProposal(proposedHeader));
		if(proposedHeader!=null)modelAndView.addObject("othersTechniques", techniqueByProposalService.othersTechniques(proposedHeader));
		modelAndView.addObject("departures",departureService.findDepartureByCountryAndStatus(userSession.getCountry(),(byte) 1));
		modelAndView.addObject("proposal",proposedHeader);
		if(proposedHeader!=null)modelAndView.addObject("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		modelAndView.setViewName("proposal");
		return modelAndView;
		
	}
	
	

	

	@PostMapping("/assessment/addproposal")
	public String addProposal(HttpServletRequest request,
								@RequestParam("idProposal") int idProposal,
								@RequestParam("idCurrencyType") int idCurrencyType,
								@RequestParam("endDate") Date endDate,
								@RequestParam("initialDate") Date initialDate,
								@RequestParam("observations") String observations,
								@RequestParam("targetText") String targetText,
								@RequestParam("idClientContact") int idClientContact,
								@RequestParam("idCollectMethod") int idCollectMethod,
								@RequestParam("idCountry") int idCountry,
								@RequestParam("idExecutionType") int idExecutionType,
								@RequestParam("idIndustrySector") int idIndustrySector,
								@RequestParam("idOperation") int idOperation,
								@RequestParam("idProposalType") int idProposalType,
								@RequestParam("idStatus") int idStatus,
								@RequestParam("idStudyCategory") int idStudyCategory,
								@RequestParam("idStudyType") int idStudyType,
								@RequestParam("techniques[]") int[] techniques,
								@RequestParam("tracker") String tracker,
								@RequestParam("projectType") String projectType,
								Model model) 
	{
		Proposal  proposal = null;
		HttpSession session = (HttpSession) request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Assessment assessment = (Assessment) session.getAttribute("assessment");
		CurrencyType currencyType = currencyTypeService.getCurrencyType(idCurrencyType);
		Settings settings = settingsService.findSettingByCountry(userSession.getCountry());
		CurrencyExchange currencyE = currencyExchangeService.findByCountryAndCurrencyType(userSession.getCountry(), settings.getCurrencyTypeInternational());
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
		User user = userService.findById(userSession.getIdUser());
		java.util.Date date = new Date();
		
		
		
		if(idProposal!=0) 
		{
				Proposal proposalToUpdate = proposalService.findByIdProposal(idProposal);
				proposal = new Proposal(idProposal,assessment.getDetail(),projectType,tracker,proposalToUpdate.getAporteFijo(),date,(double)proposalToUpdate.getCurrencyExchange(),currencyType,endDate,proposalToUpdate.getFactor1(),proposalToUpdate.getFactor2(),proposalToUpdate.getImprevisto(),initialDate,observations,targetText,assessment,clientContact,  collectMethod,  countryProposal,  executionType,industrySector,  operation,proposalType,status,studyCategory,studyType,user);	
		}
		else  proposal = new Proposal(assessment.getDetail(),projectType,tracker,settings.getAporteFijo(),date,currencyE.getSell(),currencyType,endDate,settings.getFactor1(),settings.getFactor2(),settings.getImprevisto(),initialDate,observations,targetText,assessment,clientContact,  collectMethod,  countryProposal,  executionType,industrySector,  operation,proposalType,status,studyCategory,studyType,user);
		
		proposal = proposalService.addProposal(proposal, userSession.getIdUser());
		
		LOG.info("METHOD: PROPOSAL -- PARAMS: " + proposal.toString());
		techniqueByProposalService.deleteTechiquesByProposal(proposal);
		for (int idTechnique : techniques) 
		{
			techniqueByProposalService.addTechniqueByProposal(idTechnique, proposal.getIdProposal(), userSession.getIdUser());
		}
		
		LOG.info("METHOD: PROPOSAL -- PARAMS: " + proposal.toString());
		session.setAttribute("proposedHeader",proposal);
		Proposal Proposal =  (Proposal) session.getAttribute("proposedHeader");
		LOG.info("METHOD: PROPOSAL -- PARAMS: " + proposal.toString());
		LOG.info("METHOD: PROPOSedHeader -- PARAMS: " + Proposal.toString());
		
		return "redirect:/assessment/proposal";
	}
	
	@GetMapping("/assessment/addproposal")
	public String getProposal() {
		return "redirect:/assessment/proposal";
	}
	
	
	
	@PostMapping(value = "/proposal/addproposaldetails")
	public String addProposalDetails(HttpServletRequest request,
									@RequestParam("idProposalDetails") int idProposalDetails,
									@RequestParam("detail") String detail,
									@RequestParam("parameters") String parameters,
									@RequestParam("idDeparture") int idDeparture,
									@RequestParam("price") double price,
									@RequestParam("commissionable") byte commissionable,
									@RequestParam("number") int number,
									@RequestParam("daysTimes") int daysTimes,
									@RequestParam("totalBudget") double totalBudget,
									@RequestParam("idPriceCurrencyType") int idPriceCurrencyType,
									Model model) 
	{
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		LOG.info("METHOD: ESTO ES EL user: " + userSession.toString());
		LOG.info("METHOD: ESTO ES EL PROPOSEDHEADER: " + proposedHeader.toString());
		Departure departure = departureService.findById(idDeparture);
		CurrencyType currencyType = currencyTypeService.getCurrencyType(idPriceCurrencyType);
		
		ProposalDetails proposalDetails =new ProposalDetails(idProposalDetails,detail,parameters,departure,price,commissionable,number,daysTimes,totalBudget,proposedHeader,currencyType);

				LOG.info("METHOD: addProposalDetails in ProposalController -- PARAMS: " + proposalDetails.toString());
		proposalDetailsService.addProposalDetails(proposalDetails, userSession.getIdUser());
		model.addAttribute("currencyType",userSession.getCountry().getCurrencyType());
		model.addAttribute("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		return "proposal :: #proposalDetailRow";
	}
	
	@GetMapping("/assessment/addproposaldetails")
	public String getProposalDetails(HttpServletRequest requestUser,HttpServletRequest requestProposedHeader) {
		return "redirect:/assessment/proposal";
	}
	
	@PostMapping("/proposal/customizeparameters")
	public String customizeParameters(HttpServletRequest request,@RequestParam("aporteFijo") double aporteFijo,@RequestParam("factor1") double factor1,@RequestParam("factor2") double factor2,@RequestParam("imprevisto") double imprevisto) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposal = (Proposal) session.getAttribute("proposedHeader");
		proposal.setAporteFijo(aporteFijo);
		proposal.setFactor1(factor1);
		proposal.setFactor2(factor2);
		proposal.setImprevisto(imprevisto);
		proposalService.addProposal(proposal, userSession.getIdUser());
		LOG.info("METHOD: ESTO ES EL PROPOSEDHEADER: " + proposal.toString());
		return "proposal :: #divCustomizeParameters";
	}
	
	@PostMapping("/proposal/editrowdetail")
	public String editRowDetail(HttpServletRequest request,@RequestParam("idProposalDetails") int idProposalDetails,@RequestParam("price") double price,@RequestParam("number") int number,@RequestParam("daysTimes") int daysTimes,Model model) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		ProposalDetails proposalDetails = proposalDetailsService.findById(idProposalDetails);
		double totalBudget = price*number*daysTimes;
		proposalDetails.setPrice(price);
		proposalDetails.setNumber(number);
		proposalDetails.setDaysTimes(daysTimes);
		proposalDetails.setTotalBudget(Math.rint(totalBudget*100)/100);
		proposalDetailsService.addProposalDetails(proposalDetails, userSession.getIdUser());
		model.addAttribute("currencyType",userSession.getCountry().getCurrencyType());
		model.addAttribute("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		LOG.info("METHOD: ESTO ES EL PROPOSALDETAIL DESDE LA TABLA: " + proposalDetails.toString());
		return "proposal :: #proposalDetailRow";
	}
	
	
	@PostMapping("/proposal/editrowcurrencytype")
	public String editRowCurrencyType(HttpServletRequest request,@RequestParam("idProposalDetails") int idProposalDetails,@RequestParam("idCurrencyType") int idCurrencyType,Model model) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		ProposalDetails proposalDetails = proposalDetailsService.findById(idProposalDetails);
		proposalDetails.setCurrencyType(currencyTypeService.getCurrencyType(idCurrencyType));
		proposalDetailsService.addProposalDetails(proposalDetails, userSession.getIdUser());
		model.addAttribute("currencyType",userSession.getCountry().getCurrencyType());
		model.addAttribute("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		LOG.info("METHOD: ESTO ES EL currencyType DESDE LA TABLA: " + proposalDetails.toString());
		return "proposal :: #proposalDetailRow";
	}
	
	@PostMapping("/proposal/deleteproposaldetail")
	public String deleteProposalDetail(HttpServletRequest request,@RequestParam("idProposalDetails") int idProposalDetails,Model model) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		ProposalDetails proposalDetails = proposalDetailsService.findById(idProposalDetails);
		proposalDetailsService.deleteByIdProposalDetails(idProposalDetails);
		model.addAttribute("currencyType",userSession.getCountry().getCurrencyType());
		model.addAttribute("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		LOG.info("METHOD: ESTO ES EL currencyType DESDE LA TABLA: " + proposalDetails.toString());
		return "proposal :: #proposalDetailRow";
	}
	
	
	@GetMapping("/proposal/proposalview")
	public ModelAndView proposalview(HttpServletRequest request,Model model) {
		
		HttpSession session = request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Assessment assessment = (Assessment) session.getAttribute("assessment");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		proposedHeader = proposalService.findByIdProposal(proposedHeader.getIdProposal());
		Settings sttings = settingsService.findSettingByCountry(userSession.getCountry());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formatIdProposal", proposalService.formatNumber(proposedHeader.getIdProposal()));
		modelAndView.addObject("proposalName", assessment.getDetail());
		modelAndView.addObject("settings",sttings);
		modelAndView.addObject("countryByCurrencyType", userSession.getCountry().getCurrencyType());
		modelAndView.addObject("exchangeRate", (float) proposedHeader.getCurrencyExchange());
		modelAndView.addObject("techniquesByProposal", techniqueByProposalService.findTechiquesByProposal(proposedHeader));
		modelAndView.addObject("othersTechniques", techniqueByProposalService.othersTechniques(proposedHeader));
		modelAndView.addObject("proposal",proposedHeader);
		modelAndView.addObject("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		modelAndView.setViewName("proposalview");
		return modelAndView;
	}
	
	@PostMapping("/proposal/proposalview")
	public String proposalviewPost(HttpServletRequest request,@RequestParam("idProposal") int idProposal,Model model) {
		HttpSession session = request.getSession();
		Proposal proposal = proposalService.findByIdProposal(idProposal);
		session.setAttribute("proposedHeader",proposal);
		return "redirect:/proposal/proposalview";
	}
	
	
	
	
	
	
	
}






