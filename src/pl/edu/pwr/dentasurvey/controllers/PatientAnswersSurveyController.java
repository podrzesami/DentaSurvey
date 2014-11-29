package pl.edu.pwr.dentasurvey.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.edu.pwr.dentasurvey.objects.Answer;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.PatientData;
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;
import pl.edu.pwr.dentasurvey.objects.Question;
import pl.edu.pwr.dentasurvey.objects.light.AnswerLight;
import pl.edu.pwr.dentasurvey.services.AnswerService;
import pl.edu.pwr.dentasurvey.services.AnsweredSurveyService;
import pl.edu.pwr.dentasurvey.services.PatientDataService;
import pl.edu.pwr.dentasurvey.services.PossibleAnswerService;
import pl.edu.pwr.dentasurvey.services.QuestionService;

@Controller
public class PatientAnswersSurveyController {
	@Autowired
	PatientDataService patientDataService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	PossibleAnswerService possibleAnswerService;
	
	@Autowired
	AnsweredSurveyService answeredSurveyService;
	
	@RequestMapping(value = "/survey/start", method = RequestMethod.GET)
	public ModelAndView updatePatientDataView(
			@RequestParam(value = "surveyId", required = true) Long surveyId) {

		ModelAndView model = new ModelAndView();
		
		model.setViewName("completeSurvey/answerPatientData");
		model.addObject("patient", new PatientData());
		model.addObject("surveyId", surveyId);
				
		return model;
	}
	
	@RequestMapping(value = "/survey/updatePatientData", method = RequestMethod.POST)
	public ModelAndView updatePatientData(
			@RequestParam(value = "surveyId", required = true) Long surveyId,
			@ModelAttribute PatientData patient) {

		ModelAndView model = new ModelAndView();
		patientDataService.updateOrSavePatientData(patient);
		
		model.setViewName("completeSurvey/answerSurvey");
		model.addObject("answeredSurvey", new AnsweredSurvey());
		model.addObject("surveyId", surveyId);
		model.addObject("patientId", patient.getPatientId());

		return model;
	}

	@RequestMapping(value = "/survey/updateAnsweredSurvey", method = RequestMethod.POST)
	public ModelAndView updateAnsweredSurvey(
			@RequestParam(value = "surveyId", required = true) Long surveyId,
			@ModelAttribute AnsweredSurvey answeredSurvey) {

		ModelAndView model = new ModelAndView();
		answeredSurveyService.addAnsweredSurvey(answeredSurvey);
		
		Question question = questionService.getQuestionToAnswer(surveyId, 1);
		
		if(question==null) {
			model.setViewName("completeSurvey/completed");
		}else {	
			if(question.getQuestionType().getType().contains("choice")){
				List<PossibleAnswer> possibleAnswers = possibleAnswerService
						.getPossibleAnswersForSurvey(question.getQuestionId());
				
				model.addObject("possibleAnswers", possibleAnswers);				
			}
			model.setViewName("completeSurvey/answerQuestion");
			model.addObject("surveyId", surveyId);
			model.addObject("answeredSurveyId", answeredSurvey.getAnsweredSurveyId());
			model.addObject("question", question);
			
			if(question.getQuestionType().getType().equals("multichoice")){
				model.addObject("answer", new AnswerLight());
			} else {
				model.addObject("answer", new Answer());
			}
			model.addObject("page", 1);
		}
		return model;
	}
	
	@RequestMapping(value = "/survey/answerSurvey", method = RequestMethod.GET)
	public ModelAndView answerQuestionGet(
			@RequestParam(value = "surveyId", required = true) Long surveyId,
			@RequestParam(value = "answeredSurveyId", required = false) Long answeredSurveyId,
			@RequestParam(value = "page", required = false) int page,
			@ModelAttribute Answer answer) {

		ModelAndView model = new ModelAndView();
		
		if(page==0) {
			page=1;
		} else {
			page++;
		}
		
		Question question = questionService.getQuestionToAnswer(surveyId, page);
		
		if(question==null) {
			model.setViewName("completeSurvey/completed");
		}else {	
			if(question.getQuestionType().getType().contains("choice")){
				List<PossibleAnswer> possibleAnswers = possibleAnswerService
						.getPossibleAnswersForSurvey(question.getQuestionId());
				
				model.addObject("possibleAnswers", possibleAnswers);				
			}
			model.setViewName("completeSurvey/answerQuestion");
			model.addObject("surveyId", surveyId);
			model.addObject("answeredSurveyId", answeredSurveyId);
			model.addObject("question", question);
			
			if(question.getQuestionType().getType().equals("multichoice")){
				model.addObject("answer", new AnswerLight());
			} else {
				model.addObject("answer", new Answer());
			}
			model.addObject("page", page);
		}
		return model;
	}
	
	@RequestMapping(value = "/survey/answerSurvey", method = RequestMethod.POST)
	public ModelAndView answerQuestionPost(
			@RequestParam(value = "surveyId", required = true) Long surveyId,
			@RequestParam(value = "answeredSurveyId", required = false) Long answeredSurveyId,
			@RequestParam(value = "page", required = false) int page,
			@ModelAttribute Answer answer) {

		ModelAndView model = new ModelAndView();
		if(page==0) {
			page=1;
		} else {
			page++;
		}
		
		Question question = questionService.getQuestionToAnswer(surveyId, page);
		answerService.addAnswer(answer);
		
		
		if(question==null) {
			model.setViewName("completeSurvey/completed");
		}else {	
			if(question.getQuestionType().getType().contains("choice")){
				List<PossibleAnswer> possibleAnswers = possibleAnswerService
						.getPossibleAnswersForSurvey(question.getQuestionId());
				
				model.addObject("possibleAnswers", possibleAnswers);				
			}
			model.setViewName("completeSurvey/answerQuestion");
			model.addObject("surveyId", surveyId);
			model.addObject("answeredSurveyId", answeredSurveyId);
			model.addObject("question", question);
			
			if(question.getQuestionType().getType().equals("multichoice")){
				model.addObject("answer", new AnswerLight());
			} else {
				model.addObject("answer", new Answer());
			}
			model.addObject("page", page);
		}
		return model;
	}
	
	@RequestMapping(value = "/survey/answerSurveyMultichoice", method = RequestMethod.POST)
	public ModelAndView answerQuestionMultichoicePost(
			@RequestParam(value = "surveyId", required = true) Long surveyId,
			@RequestParam(value = "answeredSurveyId", required = false) Long answeredSurveyId,
			@RequestParam(value = "page", required = false) int page,
			@ModelAttribute AnswerLight answer) {

		ModelAndView model = new ModelAndView();
		if(page==0) {
			page=1;
		} else {
			page++;
		}
		
		Question question = questionService.getQuestionToAnswer(surveyId, page);
		
		Answer ans = new Answer();
		ans.setAnswer(answer.answersToString());
		ans.setAnsweredSurvey(new AnsweredSurvey());
		ans.getAnsweredSurvey().setAnsweredSurveyId(answer.getAnsweredSurveyId());
		ans.setQuestion(new Question());
		ans.getQuestion().setQuestionId(answer.getQuestionId());
		answerService.addAnswer(ans);
		
		
		if(question==null) {
			model.setViewName("completeSurvey/completed");
		}else {	
			if(question.getQuestionType().getType().contains("choice")){
				List<PossibleAnswer> possibleAnswers = possibleAnswerService
						.getPossibleAnswersForSurvey(question.getQuestionId());
				
				model.addObject("possibleAnswers", possibleAnswers);				
			}
			model.setViewName("completeSurvey/answerQuestion");
			model.addObject("surveyId", surveyId);
			model.addObject("answeredSurveyId", answeredSurveyId);
			model.addObject("question", question);
			
			if(question.getQuestionType().getType().equals("multichoice")){
				model.addObject("answer", new AnswerLight());
			} else {
				model.addObject("answer", new Answer());
			}
			model.addObject("page", page);
		}
		return model;
	}	
}