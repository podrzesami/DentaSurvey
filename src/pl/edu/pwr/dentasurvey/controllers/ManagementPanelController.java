package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.services.PatientService;
import pl.edu.pwr.dentasurvey.services.SurveyService;
import pl.edu.pwr.dentasurvey.services.UserService;

@Controller
public class ManagementPanelController {
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/manage/survey", method = RequestMethod.GET)
	public ModelAndView displaySurveyManagementPanel() {
		ModelAndView model = new ModelAndView();

		model.addObject("surveys", surveyService.getAllSurveys());
		model.setViewName("survey/survey");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/patient", method = RequestMethod.GET)
	public ModelAndView displayPatientManagementPanel() {
		ModelAndView model = new ModelAndView();

		model.addObject("surveys", patientService.getDataForAllPatients());
		model.setViewName("patient/patient");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/user", method = RequestMethod.GET)
	public ModelAndView displayUserManagementPanel() {
		ModelAndView model = new ModelAndView();

		model.addObject("users", userService.getAllUsers());
		model.setViewName("user/user");
	 
		return model;
	}	
}
