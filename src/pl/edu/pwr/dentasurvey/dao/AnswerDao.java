package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Answer;

public interface AnswerDao {
	public List<Answer> getAllAnswers();

	public List<Answer> getAnswersForAnsSurvey(Long ansSurveyId);
	
	public List<Answer> getAnswersForQuestion(Long questionId);
		
	public Answer getAnswer(Long id);
	
	public Boolean addAnswer(Answer a);
	
	public Boolean updateAnswer(Answer a);
	
	public Boolean deleteAnswer(Long id);
	
	public Boolean deleteMultipleAnswers(Long[] ids);

	public SearchResponse getAnswersForJqgrid(Long ansSurveyId, SearchRequest req);

	public void deleteAnswersForAnsweredSurvey(Long id);	
}
