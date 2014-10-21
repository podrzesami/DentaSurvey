package pl.edu.pwr.dentasurvey.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.PatientData;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = createSessionFactory();
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    return configuration
	    		.addAnnotatedClass(PatientData.class)
	    		.addAnnotatedClass(AnsweredSurvey.class)
	    		.buildSessionFactory(serviceRegistry);	    
	}
	
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static Session getSession() {
    	Session session = null;   
    	try {         
    	       session = sessionFactory.getCurrentSession();  
    	   } catch (org.hibernate.HibernateException he) {  
    	       session = sessionFactory.openSession();     
    	   }             
    	   return session;
    }
}