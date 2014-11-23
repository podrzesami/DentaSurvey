package pl.edu.pwr.dentasurvey.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.dao.LanguageDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.objects.Language;

@Repository("languageDao")
public class LanguageDaoImpl extends AbstractDao<Language> implements LanguageDao {
	private Log log = LogFactory.getLog(Language.class);
	
	@Autowired
	public LanguageDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, Language.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<Language> getLanguagesForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<Language> getAllLanguage() {
		List<Language> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getLanguagesForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	} finally {
    		closeSession();
    	}
    	
	    return res;
	}

	@Override
	public Language getLanguage(Long id) {
		Language res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		res = getById(id);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	} finally {
    		closeSession();
    	}
    	
		return res;
	}

	
	@Override
	public Language getLanguage(String lang) {
		List<Language> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		 criteria.add(Property.forName("language").eq(lang));    
    		res = getLanguagesForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	} finally {
    		closeSession();
    	}
    	
	    return res.get(0);
	}

	
}
