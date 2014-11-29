package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Question;

public interface QuestionDao {
	public List<Question> getAllQuestions();

	public List<Question> getQuestionsForSurvey(Long surveyId);
	
	public Question getQuestion(Long id);	
	
	public Boolean addQuestion(Question q);
	
	public Boolean updateQuestion(Question q);
	
	public Boolean deleteQuestion(Long id);
	
	public Boolean deleteMultipleQuestions(Long[] ids);

	public SearchResponse getQuestionsForJqgrid(Long surveyId, SearchRequest req);

	public Boolean deleteQuestionFromAnswer(Long id);

	public Question getQestionToAnswer(Long surveyId, int page);	
}
