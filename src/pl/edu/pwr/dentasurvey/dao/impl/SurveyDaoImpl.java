package pl.edu.pwr.dentasurvey.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.dao.LanguageDao;
import pl.edu.pwr.dentasurvey.dao.QuestionDao;
import pl.edu.pwr.dentasurvey.dao.SurveyDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Language;
import pl.edu.pwr.dentasurvey.objects.Question;
import pl.edu.pwr.dentasurvey.objects.Survey;

@Repository("surveyDao")
public class SurveyDaoImpl extends AbstractDao<Survey> implements SurveyDao {
	private Log log = LogFactory.getLog(Survey.class);
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private LanguageDao languageDao;
	
	@Autowired
	public SurveyDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, Survey.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<Survey> getSurveysForCryteria(Criteria cr) {
		return cr.list();
	}	
		
	@Override
	public List<Survey> getSurveysForLanguage(String language) {
		if(language==null || language.isEmpty()) {
			return getAllSurveys();
		}
		List<Survey> res = null;
		
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Language.class);
	    ownerCriteria.setProjection(Property.forName("languageId"));
	    ownerCriteria.add(Restrictions.eq("language", language));	    
	    
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    	    Criteria criteria = createCriteria();
    	    criteria.add(Property.forName("language").in(ownerCriteria));    		
    		res = getSurveysForCryteria(criteria);
 
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
	public List<Survey> getAllSurveys() {
		List<Survey> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getSurveysForCryteria(criteria);
 
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
	public SearchResponse getSurveysForJqgrid(SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<Survey> surveys;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		totalSize = getSurveysForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc("title"));
    		} else {
    			criteria.addOrder(Order.desc("title"));
    		}
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getSurveysForCryteria(criteria);
 
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
    	
    	resp.setRows(surveys);
    	resp.setPage(req.getPage());
    	resp.setRecords(totalSize);
    	resp.setSidx(req.getSidx());
    	resp.setSord(req.getSord());
    	resp.setTotal((totalSize+req.getRows()-1)/req.getRows());
    	
	    return resp;
	}
	
	@Override
	public Survey getSurvey(Long id) {
		Survey res;
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
	public Boolean addSurvey(Survey s) {
        Boolean res = false;
    	Language l = languageDao.getLanguage(s.getLanguage().getLanguage());
    	s.setLanguage(l);    
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try{
           tx = session.beginTransaction();
           session.save(s); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    	
		return res;
	}

	@Override
	public Boolean updateSurvey(Survey s) {
        Boolean res = false;
    	Language l = languageDao.getLanguage(s.getLanguage().getLanguage());
    	s.setLanguage(l);    
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try{
           tx = session.beginTransaction();
           session.update(s); 
           tx.commit();
        }catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }finally {
           session.close(); 
        }
    	
		return res;
	}

	@Override
	public Boolean deleteSurvey(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	List<Question> questions = questionDao.getQuestionsForSurvey(id);

    	for (Iterator<Question> iterator = questions.iterator(); iterator.hasNext();) {
			Question q = iterator.next();
	        questionDao.deleteQuestion(q.getQuestionId());
		}
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Survey s = getById(id);    		
    		res = delete(s);
 
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
	public Boolean deleteMultipleSurveys(Long[] ids) {
        Boolean res = true;

        for(Long id : ids){
       		if(deleteSurvey(id) == false) {
       			res=false;
       		}
   		}
    	
		return res;
	}
}
