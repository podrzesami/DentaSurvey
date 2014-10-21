package pl.edu.pwr.dentasurvey.main;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.PatientData;

@Controller
public class MainController {
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String displayWelcomePage(ModelMap model) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List<?> result = session.createQuery("from PatientData").list();
        if (result.size() > 0) {
            Iterator<?> it = result.iterator();
            while (it.hasNext()) {
            	PatientData p = (PatientData) it.next();
            	p.getAnsweredSurveys();
            	
            	for(AnsweredSurvey a : p.getAnsweredSurveys()) {
            		System.out.println(a.getDate());
            	}
            	System.out.println(p.getName());
            }	           
        }
        
		result = session.createQuery("from AnsweredSurvey").list();
        if (result.size() > 0) {
            Iterator<?> it = result.iterator();
            while (it.hasNext()) {
            	AnsweredSurvey a = (AnsweredSurvey) it.next();	
            	System.out.println(a.getAnsweredSurveyId());
            }	           
        }	
		return "welcome";
	}
}
