package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.PossibleAnswerDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;

@Service("possibleAnswerService")
public class PossibleAnswerService {
	@Autowired
	private PossibleAnswerDao possibleAnswerDao;
	
	@Transactional
	public List<PossibleAnswer> getAllPossibleAnswers() {
		return possibleAnswerDao.getAllPossibleAnswers();
	}
	
	@Transactional
	public List<PossibleAnswer> getPossibleAnswersForSurvey(Long questionId) {
		return possibleAnswerDao.getPossibleAnswersForQuestion(questionId);
	}
	
	@Transactional
	public PossibleAnswer getPossibleAnswer(Long id) {
		return possibleAnswerDao.getPossibleAnswer(id);
	}
	
	@Transactional
	public Boolean addPossibleAnswer(PossibleAnswer q) {
		return possibleAnswerDao.addPossibleAnswer(q);
	}

	@Transactional
	public Boolean updatePossibleAnswer(PossibleAnswer q) {
		return possibleAnswerDao.updatePossibleAnswer(q);
	}

	@Transactional
	public Boolean deletePossibleAnswer(Long id) {
		return possibleAnswerDao.deletePossibleAnswer(id);
	}
	
	@Transactional
	public Boolean deleteMultiplePossibleAnswers(Long[] ids) {
		return possibleAnswerDao.deleteMultiplePossibleAnswers(ids);
	}

	@Transactional
	public SearchResponse getPossibleAnswersForJqgrid(Long questionId, SearchRequest req) {
		return possibleAnswerDao.getPossibleAnswersForJqgrid(questionId, req);
	}

}
