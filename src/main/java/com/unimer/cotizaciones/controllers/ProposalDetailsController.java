package com.unimer.cotizaciones.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.CurrencyExchange;
import com.unimer.cotizaciones.entities.CurrencyType;
import com.unimer.cotizaciones.entities.Departure;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.entities.ProposalDetails;
import com.unimer.cotizaciones.entities.Settings;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.model.UserSession;
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
@SessionAttributes({"userSession", "proposedHeader"})
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
	public ModelAndView proposalDetails(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute("proposedHeader") Proposal proposal){
		ModelAndView modelAndView = new ModelAndView();
		Country cntry = countryService.findById(userSession.getIdCountry());
		Settings sttings = settingsService.findSettingByCountry(cntry);
		User userEntity = userService.findById(userSession.getId());
		CurrencyExchange crrencyExchange = currencyExchangeService.findByCountryAndCurrencyType(cntry, sttings.getCurrencyTypeInternational());
		modelAndView.addObject("countries",countryService.listAllCountries());
		modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		modelAndView.addObject("studyCategories", studyCategoryService.listAllStudyCategories());
		modelAndView.addObject("studytypes", studyTypeService.listAllStudyTypes());
		modelAndView.addObject("industrysectors", industrySectorService.listAllIndustrySectors());
		modelAndView.addObject("techniques", techniqueService.orderlistAllTechniques());
		modelAndView.addObject("countryByCurrencyType", cntry.getCurrencyType());
		modelAndView.addObject("targets", targetService.listAllTargets());
		if(userSession.getDetailRol().equals("[ROLE_BOSS_CONTRIBUTOR]")) modelAndView.addObject("assessments", assessmentService.listAllAssessmentToHeadUser(userEntity));
		else if(userSession.getDetailRol().equals("[ROLE_ADMIN]") || userSession.getDetailRol().equals("[ROLE_ADMINISTRATOR]"))  modelAndView.addObject("assessments", assessmentService.listAllAssessmentByUserCountry(userEntity));
		else modelAndView.addObject("assessments", assessmentService.listAllByUserAssign(userEntity));
		modelAndView.addObject("clientContacts", clientContactService.findByCountry(cntry));
		modelAndView.addObject("executionTypes", executionTypeService.listAllExecutionType());
		modelAndView.addObject("autoIncrement", proposalService.autoIncrement());
		modelAndView.addObject("operations", operationService.listAllOperation());
		modelAndView.addObject("statuss", statusService.listAllStatus());
		modelAndView.addObject("proposalTypes", proposalTypeService.listAllProposalTypes());
		modelAndView.addObject("proposal",proposal);
		modelAndView.addObject("departures",departureService.findDepartureByCountryAndStatus(cntry,(byte) 1));
		modelAndView.addObject("proposaldetailss",proposalDetailsService.findByProposal(proposal));
		modelAndView.addObject("settings",sttings);
		modelAndView.addObject("currencyType",cntry.getCurrencyType());
		modelAndView.addObject("exchangeRate",crrencyExchange);
		modelAndView.setViewName("proposaldetails");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value = "/admin/addproposaldetails", method = RequestMethod.POST)
	public String addProposalDetails(ModelMap modelSession,
									@ModelAttribute("userSession") UserSession userSession,
									@ModelAttribute("proposedHeader") Proposal proposal,
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
									Model model) {
		
		double factor=0;
		if(commissionable==1) factor=factor1;
		else factor=factor2;
		Departure departure = departureService.findById(idDeparture);
		CurrencyType currencyType = currencyTypeService.getCurrencyType(idPriceCurrencyType);
		ProposalDetails proposalDetails = new ProposalDetails(idProposalDetails,aporteFijo,factor,detail, parameters,imprevisto,departure,price,commissionable,number,daysTimes,totalBudget,proposal,currencyType);
		
		LOG.info("METHOD: addProposalDetails in ProposalController -- PARAMS: " + proposalDetails.toString());
		proposalDetailsService.addProposalDetails(proposalDetails, userSession.getId());
		
		List<ProposalDetails> proposaldetailss = proposalDetailsService.findByProposal(proposal);
		 model.addAttribute("proposaldetailss", proposaldetailss);
		
		 return "proposaldetails :: #proposalDetailRow";
	}
	
	@GetMapping("/admin/addproposaldetails")
	public ModelAndView getProposalDetails(ModelMap modelSession,@ModelAttribute("userSession") UserSession userSession,@ModelAttribute("proposedHeader") Proposal proposal) {
		return proposalDetails(modelSession, userSession, proposal );
	}


}
