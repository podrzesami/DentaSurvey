package pl.edu.pwr.dentasurvey.controllers.tests;

import static org.junit.Assert.*;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.web.ModelAndViewAssert;

import pl.edu.pwr.dentasurvey.controllers.MainController;
import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.services.SurveyService;

@RunWith(MockitoJUnitRunner.class)
public class MainConterollerTest {
	
	@InjectMocks
	MainController mainController;
	
	@Mock 
	SurveyService surveyService;
		
	@Before 
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void mainPageTest() {
		//when
		ModelAndView page = mainController.displayMainPage();
		
		//then
		assertTrue("inncorrect view returned", page.getViewName().equals("main"));
	}
	
	@Test
	public void loginPageTest() {
		//when
		ModelAndView page = mainController.login(null, null);
		
		//then
		assertTrue(page.getViewName().equals("login"));
	}
	
	@Test
	public void loginPageWithErrorTest() {
		//when
		ModelAndView page = mainController.login("error", null);
		
		//then
		assertTrue(page.getViewName().equals("login"));
		ModelAndViewAssert.assertModelAttributeAvailable(page, "error");
		ModelAndViewAssert.assertModelAttributeValue(page, "error", "error");
	}
	
	@Test
	public void loginPageAfterLogoutTest() {
		//when
		ModelAndView page = mainController.login(null, "logout");
		
		//then
		assertTrue(page.getViewName().equals("login"));
		ModelAndViewAssert.assertModelAttributeAvailable(page, "msg");
		ModelAndViewAssert.assertModelAttributeValue(page, "msg", "msg");
	}
	
	@Test
	public void managePageDisplayTest() {
		//when
		ModelAndView page = mainController.displayAdminPanel();
		
		//then
		assertTrue("inncorrect view returned", page.getViewName().equals("adminPanel"));
	}
	
	@Test
	public void surveySelectPage() {
		//given
		List<Survey> surveys = new ArrayList<Survey>();
		String lang = "pl";
				
		//when
		Mockito.when(surveyService.getSurveysForLanguage(lang)).thenReturn(surveys);
		ModelAndView page = mainController.displayPatientPanel(lang);
		
		//then
		Mockito.verify(surveyService, Mockito.times(1)).getSurveysForLanguage(lang);
		assertTrue(page.getViewName().equals("surveySelection"));
		ModelAndViewAssert.assertModelAttributeAvailable(page, "surveys");
		ModelAndViewAssert.assertModelAttributeValue(page, "surveys", surveys);		
	}
}
