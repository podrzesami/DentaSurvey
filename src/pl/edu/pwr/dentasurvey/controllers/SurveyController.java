package pl.edu.pwr.dentasurvey.controllers;

import java.util.List;

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
import pl.edu.pwr.dentasurvey.objects.Language;
import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.services.LanguageService;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired
	LanguageService languageService;

	@RequestMapping(value = "/manage/survey/all", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = surveyService.getSurveysForJqgrid(req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/survey/get", method = RequestMethod.GET)
	public ModelAndView getSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		Survey survey = surveyService.getSurvey(id);
		model.addObject("survey", survey);
		model.setViewName("survey/surveyGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/survey/add", method = RequestMethod.GET)
	public ModelAndView addSurvey() {
		ModelAndView model = new ModelAndView();
		List<Language> languages = languageService.getAllLanguages();
		
		model.addObject("survey", new Survey()); 
		model.addObject("languages", languages); 
		model.setViewName("survey/surveyAdd");
	 
		return model;
	}
	@RequestMapping(value = "/manage/survey/add", method = RequestMethod.POST)
	public ModelAndView addSurvey(@ModelAttribute Survey survey) {
		ModelAndView model = new ModelAndView();

		surveyService.addSurvey(survey);
		model.setViewName("survey/survey");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/survey/update", method = RequestMethod.GET)
	public ModelAndView updateSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();
		List<Language> languages = languageService.getAllLanguages();
		
		model.addObject("languages", languages); 
		model.addObject("survey", surveyService.getSurvey(id));
		model.setViewName("survey/surveyUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/survey/update", method = RequestMethod.POST)
	public ModelAndView updateSurvey(@ModelAttribute Survey survey) {
		ModelAndView model = new ModelAndView();

		surveyService.updateSurvey(survey);
		model.setViewName("survey/survey");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/survey/delete", method = RequestMethod.GET)
	public ModelAndView deleteSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		surveyService.deleteSurvey(id);
		model.setViewName("survey/survey");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/survey/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleSurvey(@RequestParam(value="id", required=true) Long ids[]) {
		ModelAndView model = new ModelAndView();

		surveyService.deleteMultipleSurveys(ids);
		model.setViewName("survey/survey");
	 
		return model;
	}
}
