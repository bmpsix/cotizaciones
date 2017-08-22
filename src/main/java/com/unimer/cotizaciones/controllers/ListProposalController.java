package com.unimer.cotizaciones.controllers;

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
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.AssessmentService;
import com.unimer.cotizaciones.services.ClientService;
import com.unimer.cotizaciones.services.ProposalService;
import com.unimer.cotizaciones.services.StatusService;
import com.unimer.cotizaciones.services.UserService;



@Controller
public class ListProposalController {

	
	@Autowired
	@Qualifier("assessmentServiceImpl")
	private AssessmentService assessmentService;
	
	@Autowired
	@Qualifier("statusServiceImpl")
	private StatusService statusService;
	
	@Autowired
	@Qualifier("clientServiceImpl")
	private ClientService clientService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("proposalServiceImpl")
	private ProposalService proposalService;

	
	private static final Log LOG = LogFactory.getLog(ListProposalController.class);
	
	
	// Post - Guardamos el encabezado del proyecto en una variable de sesión de modo que pueda utilizada por varios metodos y vistas a la vez. Redireccionamos
		@PostMapping("/assessment/listproposal")
		public String assessmentToProposal(HttpServletRequest request,@RequestParam("idAssessment") int idAssessment) {
			HttpSession session = request.getSession();
			Assessment assessment = assessmentService.findById(idAssessment);
			session.setAttribute("assessment",assessment);
			return "redirect:/assessment/listproposal";
		}
	
		
		//Get- Se carga el modelo y la vista de "listProposal" basado en la variable de sesion del encabezado de proyecto
		@GetMapping("/assessment/listproposal")
		public ModelAndView proposalList(HttpServletRequest request){
			ModelAndView modelAndView = new ModelAndView("listProposal");
			HttpSession session = request.getSession();
			Assessment assessment = (Assessment) session.getAttribute("assessment");
			User userSession =  (User) session.getAttribute("userSession");
			modelAndView.addObject("role", userSession.getRol().getDetail().toUpperCase());
			if(userSession.getRol().getDetail().toUpperCase().equals("BOSS_CONTRIBUTOR")) modelAndView.addObject("proposals", proposalService.findByHeadUser(assessment,userSession));
			else if(userSession.getRol().getDetail().toUpperCase().equals("ADMIN") || userSession.getRol().getDetail().toUpperCase().equals("ADMINISTRATOR"))  modelAndView.addObject("proposals", proposalService.findByCountry(assessment,userSession.getCountry()));
			else modelAndView.addObject("proposals",proposalService.findByUser(assessment,userSession));
			modelAndView.addObject("clients",clientService.listAllClient());
			modelAndView.addObject("status", statusService.listAllStatus());
			modelAndView.addObject("idUser",userSession.getIdUser());
			return modelAndView;
		}

		
		// Post - Recibe los parametros y filtra la lista de propuestas
		@PostMapping("/assessment/listproposal/search")
		public String proposalSearch(HttpServletRequest request,@RequestParam("idClient") int idClient,@RequestParam("initialDate") String initialDate,@RequestParam("endDate") String endDate,@RequestParam("idStatus") int idStatus ,Model model) {
			HttpSession session = request.getSession();
			User userSession =  (User) session.getAttribute("userSession");
			LOG.info("/assessment/listproposal/search INITIALdate "  + initialDate+" CONTENIDO DE ENDdete "+endDate);
			Assessment assessment = (Assessment) session.getAttribute("assessment");
			model.addAttribute("role", userSession.getRol().getDetail().toUpperCase());
			model.addAttribute("idUser",userSession.getIdUser());
			model.addAttribute("proposals",proposalService.filterProposal(assessment, idClient, initialDate, endDate, idStatus));
			return "listProposal :: #listProposalRow";
		}

		
		
		
		
		
		
		
		
}
