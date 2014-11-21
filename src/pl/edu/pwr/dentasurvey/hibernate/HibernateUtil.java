package pl.edu.pwr.dentasurvey.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.objects.Answer;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.Language;
import pl.edu.pwr.dentasurvey.objects.PatientData;
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;
import pl.edu.pwr.dentasurvey.objects.Question;
import pl.edu.pwr.dentasurvey.objects.QuestionCategory;
import pl.edu.pwr.dentasurvey.objects.QuestionType;
import pl.edu.pwr.dentasurvey.objects.Role;
import pl.edu.pwr.dentasurvey.objects.Survey;
import pl.edu.pwr.dentasurvey.objects.User;

@Repository("hibernateUtil")
public class HibernateUtil {
	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;

	public SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    return configuration
	    		.addAnnotatedClass(Answer.class)
	    		.addAnnotatedClass(AnsweredSurvey.class)
	    		.addAnnotatedClass(Language.class)
	    		.addAnnotatedClass(PatientData.class)
	    		.addAnnotatedClass(Question.class)
	    		.addAnnotatedClass(QuestionType.class)
	    		.addAnnotatedClass(QuestionCategory.class)
	    		.addAnnotatedClass(PossibleAnswer.class)
	    		.addAnnotatedClass(Role.class)
	    		.addAnnotatedClass(Survey.class)
	    		.addAnnotatedClass(User.class)
	    		.buildSessionFactory(serviceRegistry);	    
	}
	
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public Session getSession() {
    	Session session = null;   
    	try {         
    	       session = sessionFactory.getCurrentSession();  
    	   } catch (org.hibernate.HibernateException he) {  
    	       session = sessionFactory.openSession();     
    	   }             
    	   return session;
    }

	public HibernateUtil() {
		this.sessionFactory = createSessionFactory();
	}    
}