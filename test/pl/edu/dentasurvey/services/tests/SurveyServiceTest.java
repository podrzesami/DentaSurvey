package pl.edu.dentasurvey.services.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import pl.edu.pwr.dentasurvey.dao.SurveyDao;
import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@RunWith(MockitoJUnitRunner.class)
public class SurveyServiceTest {
	
	@InjectMocks
	SurveyService surveyService;
	
	@Mock
	SurveyDao surveyDao;
		
	@Before 
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getSurveysForLanguageTest() {
		//given
		List<Survey> surveys = new ArrayList<Survey>();
		String lang = "pl";
				
		//when
		Mockito.when(surveyDao.getSurveysForLanguage(lang)).thenReturn(surveys);
		List<Survey> result = surveyService.getSurveysToAnswer(lang);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).getSurveysForLanguage(lang);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", surveys==result);		
	}
	
	@Test
	public void getAllSurveysTest() {
		//given
		List<Survey> surveys = new ArrayList<Survey>();
				
		//when
		Mockito.when(surveyDao.getAllSurveys()).thenReturn(surveys);
		List<Survey> result = surveyService.getAllSurveys();
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).getAllSurveys();
		assertNotNull("empty result", result);
		assertTrue("incorrect result", surveys==result);
	}
	
	@Test
	public void getSurvey() {
		//given
		Survey survey = new Survey();
		Long id = 1L;
				
		//when
		Mockito.when(surveyDao.getSurvey(id)).thenReturn(survey);
		Survey result = surveyService.getSurvey(id);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).getSurvey(id);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", survey==result);	}
	
	@Test
	public void addSurvey() {
		//given
		Survey survey = new Survey();		
				
		//when
		Mockito.when(surveyDao.addSurvey(survey)).thenReturn(true);
		Boolean result = surveyService.addSurvey(survey);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).addSurvey(survey);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", result==true);
	}
	
	@Test
	public void updateSurvey() {
		//given
		Survey survey = new Survey();		
				
		//when
		Mockito.when(surveyDao.updateSurvey(survey)).thenReturn(true);
		Boolean result = surveyService.updateSurvey(survey);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).updateSurvey(survey);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", result==true);	
	}
	
	@Test
	public void deleteSurvey() {
		//given
		Long id = 1L;
				
		//when
		Mockito.when(surveyDao.deleteSurvey(id)).thenReturn(true);
		Boolean result = surveyService.deleteSurvey(id);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).deleteSurvey(id);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", result==true);
	}
	
	@Test
	public void deleteMultipleSurveys() {
		//given
		Long ids[] = {1L, 2L, 3L};
				
		//when
		Mockito.when(surveyDao.deleteMultipleSurveys(ids)).thenReturn(true);
		Boolean result = surveyService.deleteMultipleSurveys(ids);
		
		//then
		Mockito.verify(surveyDao, Mockito.times(1)).deleteMultipleSurveys(ids);
		assertNotNull("empty result", result);
		assertTrue("incorrect result", result==true);

	}	
}
