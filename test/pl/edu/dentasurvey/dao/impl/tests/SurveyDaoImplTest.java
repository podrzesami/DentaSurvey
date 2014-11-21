package pl.edu.dentasurvey.dao.impl.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.edu.pwr.dentasurvey.dao.SurveyDao;
import pl.edu.pwr.dentasurvey.objects.Survey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml"})
@WebAppConfiguration
public class SurveyDaoImplTest {

	@Autowired
	SurveyDao surveyDao;
	
	@Test
	public void getSurveysForLanguageTest() {
		//given
		String lang = "pl";
		
		//when
		List<Survey> surveys = surveyDao.getSurveysForLanguage(lang);
		
		//then
		assertNotNull("null resurts", surveys);
		assertFalse("list empty", surveys.isEmpty());
		assertTrue("size shoulg be greater than 1", surveys.size()>1);
	}
	
	@Test
	public void getAllSurveysTest() {
		//when
		List<Survey> surveys = surveyDao.getAllSurveys();
		
		//then
		assertNotNull("null resurts", surveys);
		assertFalse("list empty", surveys.isEmpty());
		assertTrue("size shoulg be greater than 1", surveys.size()>1);
	}
	
	@Test
	public void getSurveyTest() {
		//given
		Long id = 1L;
		
		//when
		Survey s = surveyDao.getSurvey(id);
		
		//then 
		assertNotNull("null result", s);
	}
}
