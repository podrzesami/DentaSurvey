package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.QuestionType;

public interface QuestionTypeDao {
	public List<QuestionType> getAllQuestionType();
	
	public QuestionType getQuestionType(Long id);	
	
	public QuestionType getQuestionType(String type);
}
