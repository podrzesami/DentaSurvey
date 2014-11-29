package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.services.AnsweredSurveyService;
import pl.edu.pwr.dentasurvey.services.PatientDataService;

@Controller
public class AnsweredSurveyController {

	@Autowired
	PatientDataService patientDataService;
	
	@Autowired
	AnsweredSurveyService answeredSurveyService;

	@RequestMapping(value = "/manage/answeredSurvey/answeredSurveys", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@RequestParam(value="id", required=true) Long id,
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = answeredSurveyService.getAnsweredSurveysForJqgrid(id,req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/answeredSurvey/get", method = RequestMethod.GET)
	public ModelAndView getSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		AnsweredSurvey answeredSurvey = answeredSurveyService.getAnsweredSurvey(id);
		model.addObject("answeredSurvey", answeredSurvey);
		model.setViewName("answeredSurvey/answeredSurveyGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/answeredSurvey/delete", method = RequestMethod.GET)
	public ModelAndView deleteSurvey(
			@RequestParam(value="patientId", required=true) Long patientId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		answeredSurveyService.deleteAnsweredSurvey(id);
		model.addObject("patient", patientDataService.getPatientData(patientId));
		model.setViewName("patient/patientGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/answeredSurvey/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleSurvey(
			@RequestParam(value="patientId", required=true) Long patientId,
			@RequestParam(value="id", required=true) Long ids[]) {
		ModelAndView model = new ModelAndView();

		answeredSurveyService.deleteMultipleAnsweredSurveys(ids);
		model.addObject("patient", patientDataService.getPatientData(patientId));
		model.setViewName("patient/patientGet");
	 
		return model;
	}
}
