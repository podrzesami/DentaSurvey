package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.objects.QuestionCategory;

public interface QuestionCategoryDao {
	public List<QuestionCategory> getAllQuestionCategory();
	
	public QuestionCategory getQuestionCategory(Long id);	
	
	public QuestionCategory getQuestionCategory(String category);
}
