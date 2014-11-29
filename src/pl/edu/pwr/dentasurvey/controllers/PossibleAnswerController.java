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
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;
import pl.edu.pwr.dentasurvey.services.PossibleAnswerService;
import pl.edu.pwr.dentasurvey.services.QuestionService;

@Controller
public class PossibleAnswerController {
	
	@Autowired
	PossibleAnswerService possibleAnswerService;
	
	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/manage/possibleAnswer/answers", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@RequestParam(value="id", required=true) Long id,
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = possibleAnswerService.getPossibleAnswersForJqgrid(id,req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/possibleAnswer/get", method = RequestMethod.GET)
	public ModelAndView getSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		PossibleAnswer possibleAnswer = possibleAnswerService.getPossibleAnswer(id);
		model.addObject("possibleAnswer", possibleAnswer);
		model.setViewName("possibleAnswer/possibleAnswerGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/possibleAnswer/add", method = RequestMethod.GET)
	public ModelAndView addSurvey(
			@RequestParam(value="questionId", required=true) Long questionId
			) {		
		ModelAndView model = new ModelAndView();
	
		model.addObject("possibleAnswer", new PossibleAnswer()); 
		model.addObject("questionId", questionId);
		model.setViewName("possibleAnswer/possibleAnswerAdd");
	 
		return model;
	}
	@RequestMapping(value = "/manage/possibleAnswer/add", method = RequestMethod.POST)
	public ModelAndView addSurvey(
			@ModelAttribute PossibleAnswer possibleAnswer) {
		ModelAndView model = new ModelAndView();
		possibleAnswerService.addPossibleAnswer(possibleAnswer);
		
		model.addObject("question", possibleAnswer.getQuestion());
		model.setViewName("question/questionGet");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/possibleAnswer/update", method = RequestMethod.GET)
	public ModelAndView updateSurvey(
			@RequestParam(value="questionId", required=true) Long questionId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("possibleAnswer", possibleAnswerService.getPossibleAnswer(id));
		model.addObject("questionId", questionId);
		model.setViewName("possibleAnswer/possibleAnswerUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/possibleAnswer/update", method = RequestMethod.POST)
	public ModelAndView updateSurvey(
				@ModelAttribute PossibleAnswer possibleAnswer) {
		ModelAndView model = new ModelAndView();

		possibleAnswerService.updatePossibleAnswer(possibleAnswer);
		model.addObject("question", possibleAnswer.getQuestion());
		model.setViewName("question/questionGet");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/possibleAnswer/delete", method = RequestMethod.GET)
	public ModelAndView deleteSurvey(
			@RequestParam(value="questionId", required=true) Long questionId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		possibleAnswerService.deletePossibleAnswer(id);
		model.addObject("question", questionService.getQuestion(questionId));
		model.setViewName("question/questionGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/possibleAnswer/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleSurvey(
			@RequestParam(value="questionId", required=true) Long questionId,
			@RequestParam(value="id", required=true) Long ids[]) {
		ModelAndView model = new ModelAndView();

		possibleAnswerService.deleteMultiplePossibleAnswers(ids);
		model.addObject("question", questionService.getQuestion(questionId));
		model.setViewName("question/questionGet");
	 
		return model;
	}
}
