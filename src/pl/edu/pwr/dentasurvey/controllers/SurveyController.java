package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@Controller
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@RequestMapping(value = "/manage/survey/add", method = RequestMethod.GET)
	public ModelAndView addSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.setViewName("survey/surveyAdd");
	 
		return model;
	}
	@RequestMapping(value = "/manage/survey/add", method = RequestMethod.POST)
	public ModelAndView addSurvey(@ModelAttribute Survey s) {
		ModelAndView model = new ModelAndView();

		surveyService.addSurvey(s);
		model.setViewName("survey/survey");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/survey/update", method = RequestMethod.GET)
	public ModelAndView updateSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		model.addObject("survey", surveyService.getSurvey(id));
		model.setViewName("survey/surveyUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/survey/update", method = RequestMethod.POST)
	public ModelAndView updateSurvey(@ModelAttribute Survey s) {
		ModelAndView model = new ModelAndView();

		surveyService.updateSurvey(s);
		model.setViewName("survey/survey");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/survey/delete", method = RequestMethod.POST)
	public ModelAndView deleteSurvey(@ModelAttribute Long id) {
		ModelAndView model = new ModelAndView();

		surveyService.deleteSurvey(id);
		model.setViewName("survey/survey");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/survey/multipleDelete", method = RequestMethod.POST)
	public ModelAndView deleteSurvey(@ModelAttribute Long[] ids) {
		ModelAndView model = new ModelAndView();

		surveyService.deleteMultipleSurveys(ids);
		model.setViewName("survey/survey");
	 
		return model;
	}
}