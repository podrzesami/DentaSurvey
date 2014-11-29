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
import pl.edu.pwr.dentasurvey.objects.Question;
import pl.edu.pwr.dentasurvey.objects.QuestionCategory;
import pl.edu.pwr.dentasurvey.objects.QuestionType;
import pl.edu.pwr.dentasurvey.services.QuestionCategoryService;
import pl.edu.pwr.dentasurvey.services.QuestionService;
import pl.edu.pwr.dentasurvey.services.QuestionTypeService;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@Controller
public class QuestionController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired
	QuestionCategoryService questionCategoryService;
	
	@Autowired
	QuestionTypeService questionTypeService;
	
	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/manage/question/questions", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@RequestParam(value="id", required=true) Long id,
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = questionService.getQuestionsForJqgrid(id,req);
		return resp;
	}	
	
	@RequestMapping(value = "/manage/question/get", method = RequestMethod.GET)
	public ModelAndView getSurvey(@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		Question question = questionService.getQuestion(id);
		model.addObject("question", question);
		model.setViewName("question/questionGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/question/add", method = RequestMethod.GET)
	public ModelAndView addSurvey(
			@RequestParam(value="surveyId", required=true) Long surveyId
			) {		
		ModelAndView model = new ModelAndView();
		List<QuestionCategory> categories = questionCategoryService.getAllQuestionCategorys();
		List<QuestionType> types = questionTypeService.getAllQuestionTypes();
		
		model.addObject("question", new Question()); 
		model.addObject("categories", categories); 
		model.addObject("types", types); 
		model.addObject("surveyId", surveyId);
		model.setViewName("question/questionAdd");
	 
		return model;
	}
	@RequestMapping(value = "/manage/question/add", method = RequestMethod.POST)
	public ModelAndView addSurvey(
			@ModelAttribute Question question) {
		ModelAndView model = new ModelAndView();
		questionService.addQuestion(question);
		
		model.addObject("survey", question.getSurvey());
		model.setViewName("survey/surveyGet");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/question/update", method = RequestMethod.GET)
	public ModelAndView updateSurvey(
			@RequestParam(value="surveyId", required=true) Long surveyId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();
		List<QuestionCategory> categories = questionCategoryService.getAllQuestionCategorys();
		List<QuestionType> types = questionTypeService.getAllQuestionTypes();
		
		model.addObject("categories", categories); 
		model.addObject("types", types); 
		model.addObject("question", questionService.getQuestion(id));
		model.addObject("surveyId", surveyId);
		model.setViewName("question/questionUpdate");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/question/update", method = RequestMethod.POST)
	public ModelAndView updateSurvey(
				@ModelAttribute Question question) {
		ModelAndView model = new ModelAndView();

		questionService.updateQuestion(question);
		model.addObject("survey", question.getSurvey());
		model.setViewName("survey/surveyGet");
	 
		return model;
	}	
	
	@RequestMapping(value = "/manage/question/delete", method = RequestMethod.GET)
	public ModelAndView deleteSurvey(
			@RequestParam(value="surveyId", required=true) Long surveyId,
			@RequestParam(value="id", required=true) Long id) {
		ModelAndView model = new ModelAndView();

		questionService.deleteQuestion(id);
		model.addObject("survey", surveyService.getSurvey(surveyId));
		model.setViewName("survey/surveyGet");
	 
		return model;
	}
	
	@RequestMapping(value = "/manage/question/multipleDelete", method = RequestMethod.GET)
	public ModelAndView deleteMultipleSurvey(
			@RequestParam(value="surveyId", required=true) Long surveyId,
			@RequestParam(value="id", required=true) Long ids[]) {
		ModelAndView model = new ModelAndView();

		questionService.deleteMultipleQuestions(ids);
		model.addObject("survey", surveyService.getSurvey(surveyId));
		model.setViewName("survey/surveyGet");
	 
		return model;
	}
}
