package pl.edu.pwr.dentasurvey.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pwr.dentasurvey.dao.AnsweredSurveyDao;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;

@Service("answeredSurveyService")
public class AnsweredSurveyService {
	@Autowired
	AnsweredSurveyDao answeredSurveyDao;
	
	@Transactional
	public List<AnsweredSurvey> getAllAnsweredSurveys() {
		return answeredSurveyDao.getAllAnsweredSurveys();
	}
	
	@Transactional
	public AnsweredSurvey getAnsweredSurvey(Long id) {
		return answeredSurveyDao.getAnsweredSurvey(id);
	}
	
	@Transactional
	public Boolean addAnsweredSurvey(AnsweredSurvey as) {
		return answeredSurveyDao.addAnsweredSurvey(as);
	}
	
	@Transactional
	public Boolean updateAnsweredSurvey(AnsweredSurvey as) {
		return answeredSurveyDao.updateAnsweredSurvey(as);
	}
	
	@Transactional
	public Boolean deleteAnsweredSurvey(Long id) {
		return answeredSurveyDao.deleteAnsweredSurvey(id);
	}
	@Transactional
	public Boolean deleteMultipleAnsweredSurveys(Long[] ids) {
		return answeredSurveyDao.deleteMultipleAnsweredSurveys(ids);
	}
	
	@Transactional
	public SearchResponse getAnsweredSurveysForJqgrid(Long id, SearchRequest req) {
		return answeredSurveyDao.getAnsweredSurveysForJqgrid(id, req);
	}
}
