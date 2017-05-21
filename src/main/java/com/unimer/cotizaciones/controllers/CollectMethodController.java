package com.unimer.cotizaciones.controllers;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.unimer.cotizaciones.entities.CollectMethod;
import com.unimer.cotizaciones.entities.User;
import com.unimer.cotizaciones.services.CollectMethodService;

@Controller
@SessionAttributes({"userSession"})
public class CollectMethodController {
	
	@Autowired
	@Qualifier("collectMethodServiceImpl")
	private CollectMethodService collectMethodService;
	
	
	//private static final Log LOG = LogFactory.getLog(CollectMethodController.class);
	
	@GetMapping("/admin/collectmethod")
	public ModelAndView collectMethod() {
		
		ModelAndView mvn = new ModelAndView();
		mvn.addObject("collectmethods", collectMethodService.listAllCollectMethod());
		mvn.setViewName("collectmethod");
		return mvn;
	}
	
	@PostMapping("/admin/addcollectmethod")
	public String addCollectMethod(ModelMap modelSession,@ModelAttribute("userSession") User userSession,@ModelAttribute(name = "collectMethod") CollectMethod collectMethod, Model model){
		collectMethodService.addCollectMethod(collectMethod,userSession.getIdUser()); 
		return "redirect:/admin/collectmethod";
	}
	
	@GetMapping("/admin/addcollectmethod")
	public String getCollectMethod(){
		return "redirect:/admin/collectmethod";
	}
	
	@GetMapping("/admin/chargecollectmethod")
	public ModelAndView chargeCollectMethod(int idCollectMethod, Model model) {

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("collectmethod");
			modelAndView.addObject("collectmethods", collectMethodService.listAllCollectMethod());
			modelAndView.addObject("updateCollectMethod", collectMethodService.getCollectMethod(idCollectMethod));
			
		return modelAndView;
	}
	
	
}
