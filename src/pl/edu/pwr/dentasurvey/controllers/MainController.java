package pl.edu.pwr.dentasurvey.controllers;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@Controller
public class MainController {
	
	@Autowired 
	private SurveyService surveyService;	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayMainPage() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("role", auth.getAuthorities().toString());
		model.setViewName("main");
		
		return model;
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView displayAdminPanel() {
		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("role", auth.getAuthorities().toString());
		model.setViewName("adminPanel");
	 
		return model;
	}
	
	@RequestMapping(value = "/survery/selectSurvery", method = RequestMethod.GET)
	public ModelAndView displayPatientPanel() {
		Locale locale = LocaleContextHolder.getLocale();
		ModelAndView model = new ModelAndView();
		model.setViewName("surveySelection");
		List<Survey> surveys = surveyService.getSurveysToAnswer(locale.getLanguage());
		model.addObject("surveys", surveys);
				
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
				@RequestParam(value = "logout", required = false) String logout) {
	 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "error");
		}
	 
		if (logout != null) {
			model.addObject("msg", "msg");
		}
		model.setViewName("login");
	 
		return model;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("403");
	 
		return model;
	}
}