package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pwr.dentasurvey.dao.SurveyDao;
import pl.edu.pwr.dentasurvey.objects.Survey;

@Service("surveyService")
public class SurveyService {
	
	@Autowired 
	private SurveyDao surveyDao;
	
	@Transactional
	public List<Survey> getSurveysForLanguage(String language) {
		return surveyDao.getSurveysForLanguage(language);
	}
	
	@Transactional
	public List<Survey> getAllSurveys() {
		return surveyDao.getAllSurveys();
	}
	
	@Transactional
	public Survey getSurvey(Long id) {
		return surveyDao.getSurvey(id);
	}
	
	@Transactional
	public Boolean addSurvey(Survey s) {
		return surveyDao.addSurvey(s);
	}
	
	@Transactional
	public Boolean updateSurvey(Survey s) {
		return surveyDao.updateSurvey(s);
	}
	
	@Transactional
	public Boolean deleteSurvey(Long id) {
		return surveyDao.deleteSurvey(id);
	}
	
	@Transactional
	public Boolean deleteMultipleSurveys(Long[] ids) {
		return surveyDao.deleteMultipleSurveys(ids);
	}	
}
