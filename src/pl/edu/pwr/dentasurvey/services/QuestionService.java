package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.QuestionDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Question;

@Service("questionService")
public class QuestionService {
	@Autowired
	private QuestionDao questionDao;
	
	@Transactional
	public List<Question> getAllQuestions() {
		return questionDao.getAllQuestions();
	}
	
	@Transactional
	public List<Question> getQuestionsForSurvey(Long surveyId) {
		return questionDao.getQuestionsForSurvey(surveyId);
	}
	
	@Transactional
	public Question getQuestion(Long id) {
		return questionDao.getQuestion(id);
	}
	
	@Transactional
	public Boolean addQuestion(Question q) {
		return questionDao.addQuestion(q);
	}

	@Transactional
	public Boolean updateQuestion(Question q) {
		return questionDao.updateQuestion(q);
	}

	@Transactional
	public Boolean deleteQuestion(Long id) {
		return questionDao.deleteQuestion(id);
	}
	
	@Transactional
	public Boolean deleteMultipleQuestions(Long[] ids) {
		return questionDao.deleteMultipleQuestions(ids);
	}

	@Transactional
	public SearchResponse getQuestionsForJqgrid(Long surveyId, SearchRequest req) {
		return questionDao.getQuestionsForJqgrid(surveyId, req);
	}

	@Transactional
	public Question getQuestionToAnswer(Long surveyId, int page) {
		return questionDao.getQestionToAnswer(surveyId, page);
	}

}
