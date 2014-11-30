package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.AnswerDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Answer;

@Service("answerService")
public class AnswerService {
	
	@Autowired
	AnswerDao answerDao;
	
	@Transactional
	public SearchResponse getAnswersForJqgrid(Long ansSurveyId, SearchRequest req ){
		return answerDao.getAnswersForJqgrid(ansSurveyId, req);
	}

	@Transactional
	public List<Answer> getAnswersForSurvey(Long ansSurveyId){
		return answerDao.getAnswersForAnsSurvey(ansSurveyId);
	}
	
	@Transactional
	public Boolean addAnswer(Answer answer) {
		return answerDao.addAnswer(answer);
	}
}
