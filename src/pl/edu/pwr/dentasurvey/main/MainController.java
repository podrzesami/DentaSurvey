package pl.edu.pwr.dentasurvey.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
/*	@RequestMapping(value = "/main", method = RequestMethod.GET)
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
*/	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayStartPage(ModelMap model) {
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
	 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "error");
		}
	 
		if (logout != null) {
			model.addObject("msg", "msg");
		}
		model.setViewName("login");
	 
		return model;
	}
}
