package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;

public interface PossibleAnswerDao {
	public List<PossibleAnswer> getAllPossibleAnswers();

	public List<PossibleAnswer> getPossibleAnswersForQuestion(Long questionId);
	
	public PossibleAnswer getPossibleAnswer(Long id);	
	
	public Boolean addPossibleAnswer(PossibleAnswer ans);
	
	public Boolean updatePossibleAnswer(PossibleAnswer ans);
	
	public Boolean deletePossibleAnswer(Long id);
	
	public Boolean deleteMultiplePossibleAnswers(Long[] ids);

	public SearchResponse getPossibleAnswersForJqgrid(Long questionId, SearchRequest req);	
}
