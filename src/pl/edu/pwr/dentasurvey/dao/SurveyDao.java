package pl.edu.pwr.dentasurvey.dao;

import java.util.List;

import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Survey;

public interface SurveyDao {
	public List<Survey> getSurveysForLanguage(String language);
	
	public List<Survey> getAllSurveys();
	
	public Survey getSurvey(Long id);	
	
	public Boolean addSurvey(Survey s);
	
	public Boolean updateSurvey(Survey s);
	
	public Boolean deleteSurvey(Long id);
	
	public Boolean deleteMultipleSurveys(Long[] ids);

	public SearchResponse getSurveysForJqgrid(SearchRequest req);	
}
