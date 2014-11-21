package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.QuestionType;

public interface QuestionTypeDao {
	public QuestionType getQuestionType(Long id);
	
	public List<QuestionType> getAllQuestionTypes();
}
