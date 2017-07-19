package com.unimer.cotizaciones.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.CollectMethodService;
import com.unimer.cotizaciones.services.CountryByCurrencyTypeService;
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
import com.unimer.cotizaciones.services.TechniqueService;
import com.unimer.cotizaciones.services.UserService;
import com.unimer.cotizaciones.services.impl.ClientContactServiceImpl;

@Controller
public class ProposalDetailsController {

	private static final Log LOG = LogFactory.getLog(ProposalDetailsController.class);
	
	

	@Autowired
	@Qualifier("proposalDetailsServiceImpl")
	private ProposalDetailsService proposalDetailsService;
	
	@Autowired
	@Qualifier("departureServiceImpl")
	private DepartureService departureService;
	
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
	@Qualifier("countryByCurrencyTypeServiceImpl")
	private CountryByCurrencyTypeService countryByCurrencyTypeService;
	
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
	
	
	
	@GetMapping("/admin/proposaldetails")
	public ModelAndView proposalDetails(HttpServletRequest request){
		HttpSession session = (HttpSession) request.getSession();
		User userSession =  (User) session.getAttribute("userSession");
		Proposal proposedHeader = (Proposal) session.getAttribute("proposedHeader");
		LOG.info("METHOD: ESTO ES EL user: " + userSession.toString());
		LOG.info("METHOD: ESTO ES EL PROPOSEDHEADER: " + proposedHeader.toString());
		ModelAndView modelAndView = new ModelAndView();
		Settings sttings = settingsService.findSettingByCountry(userSession.getCountry());
		CurrencyExchange crrencyExchange = currencyExchangeService.findByCountryAndCurrencyType(userSession.getCountry(), sttings.getCurrencyTypeInternational());
		modelAndView.addObject("countries",countryService.listAllCountries());
		modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("studytypes", studyTypeService.listAllStudyTypes());
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		modelAndView.addObject("techniques", techniqueService.orderlistAllTechniques());
		modelAndView.addObject("countryByCurrencyType", userSession.getCountry().getCurrencyType());
		modelAndView.addObject("targets", targetService.listAllTargets());
		if(userSession.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) modelAndView.addObject("assessments", assessmentService.listAllAssessmentToHeadUser(userSession));
		else if(userSession.getRol().getDetail().toUpperCase().equals("ADMIN") || userSession.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR"))  modelAndView.addObject("assessments", assessmentService.listAllAssessmentByUserCountry(userSession));
		else modelAndView.addObject("assessments", assessmentService.listAllByUserAssign(userSession));
		modelAndView.addObject("clientContacts", clientContactService.findByCountry(userSession.getCountry()));
		modelAndView.addObject("executionTypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("autoIncrement", proposalService.autoIncrement());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("statuss", statusService.listAllStatus());
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.addObject("proposal",proposedHeader);
		modelAndView.addObject("departures",departureService.findDepartureByCountryAndStatus(userSession.getCountry(),(byte) 1));
		modelAndView.addObject("proposaldetailss",proposalDetailsService.findByProposal(proposedHeader));
		modelAndView.addObject("settings",sttings);
		modelAndView.addObject("currencyType",userSession.getCountry().getCurrencyType());
		modelAndView.addObject("exchangeRate",crrencyExchange);
		modelAndView.setViewName("proposaldetails");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/admin/addproposaldetails", method = RequestMethod.POST)
	public String addProposalDetails(HttpServletRequest request,
									@RequestParam("idProposalDetails") int idProposalDetails,
									@RequestParam("aporteFijo") double aporteFijo,
									@RequestParam("factor1") double factor1,
									@RequestParam("factor2") double factor2,
									@RequestParam("detail") String detail,
									@RequestParam("parameters") String parameters,
									@RequestParam("imprevisto") double imprevisto,
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
		double factor=0;
		if(commissionable==1) factor=factor1;
		else factor=factor2;
		Departure departure = departureService.findById(idDeparture);
		CurrencyType currencyType = currencyTypeService.getCurrencyType(idPriceCurrencyType);
		ProposalDetails proposalDetails = new ProposalDetails(idProposalDetails,aporteFijo,factor,detail, parameters,imprevisto,departure,price,commissionable,number,daysTimes,totalBudget,proposedHeader,currencyType);
		LOG.info("METHOD: addProposalDetails in ProposalController -- PARAMS: " + proposalDetails.toString());
		proposalDetailsService.addProposalDetails(proposalDetails, userSession.getIdUser());
		List<ProposalDetails> proposaldetailss = proposalDetailsService.findByProposal(proposedHeader);
		model.addAttribute("proposaldetailss", proposaldetailss);
		return "proposaldetails :: #proposalDetailRow";
	}
	
	@GetMapping("/admin/addproposaldetails")
	public String getProposalDetails(HttpServletRequest requestUser,HttpServletRequest requestProposedHeader) {
		return "redirect:/admin/proposaldetails";
	}


}
