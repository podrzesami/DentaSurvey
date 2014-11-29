package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;

public interface AnsweredSurveyDao {
	public List<AnsweredSurvey> getAllAnsweredSurveys();
	
	public AnsweredSurvey getAnsweredSurvey(Long id);	
	
	public Boolean addAnsweredSurvey(AnsweredSurvey as);
	
	public Boolean updateAnsweredSurvey(AnsweredSurvey as);
	
	public Boolean deleteAnsweredSurvey(Long id);
	
	public Boolean deleteMultipleAnsweredSurveys(Long[] ids);

	public SearchResponse getAnsweredSurveysForJqgrid(Long id, SearchRequest req);

	public void deleteAnsweredSurveysForPatient(Long id);

	public List<AnsweredSurvey> getAnsweredSurveysForPatient(Long id);	
}
