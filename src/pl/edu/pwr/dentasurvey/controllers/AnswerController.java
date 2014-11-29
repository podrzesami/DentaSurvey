package pl.edu.pwr.dentasurvey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.services.AnswerService;

@Controller
public class AnswerController {
	@Autowired
	AnswerService answerService;
	
	@RequestMapping(value = "/manage/answer/answers", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody SearchResponse getAllSurveys(
			@RequestParam(value="id", required=true) Long id,
			@ModelAttribute SearchRequest req
			) {		
		SearchResponse resp = answerService.getAnswersForJqgrid(id,req);
		return resp;
	}	
}
