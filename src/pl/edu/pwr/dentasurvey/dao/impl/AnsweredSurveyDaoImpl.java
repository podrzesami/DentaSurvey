package pl.edu.pwr.dentasurvey.dao.impl;

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

import pl.edu.pwr.dentasurvey.dao.AnswerDao;
import pl.edu.pwr.dentasurvey.dao.AnsweredSurveyDao;
import pl.edu.pwr.dentasurvey.dao.PatientDataDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.PatientData;

@Repository("answeredSurveyDao")
public class AnsweredSurveyDaoImpl extends AbstractDao<AnsweredSurvey> implements AnsweredSurveyDao {
	private Log log = LogFactory.getLog(AnsweredSurvey.class);
	
	@Autowired
	PatientDataDao patientDataDao;
	
	@Autowired
	AnswerDao answerDao;
	
	@Autowired
	public AnsweredSurveyDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, AnsweredSurvey.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<AnsweredSurvey> getAnsweredSurveysForCryteria(Criteria cr) {
		return cr.list();
	}	
		
	@Override
	public List<AnsweredSurvey> getAllAnsweredSurveys() {
		List<AnsweredSurvey> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getAnsweredSurveysForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
    	
	    return res;
	}
	
	@Override
	public SearchResponse getAnsweredSurveysForJqgrid(Long id, SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<AnsweredSurvey> surveys;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
    	
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(PatientData.class);
	    ownerCriteria.setProjection(Property.forName("patientId"));
	    ownerCriteria.add(Restrictions.eq("patientId", id));
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("patientData").in(ownerCriteria)); 
    		totalSize = getAnsweredSurveysForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc(req.getSidx()));
    		} else {
    			criteria.addOrder(Order.desc(req.getSidx()));
    		}
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getAnsweredSurveysForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
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
	public List<AnsweredSurvey> getAnsweredSurveysForPatient(Long id) {
		List<AnsweredSurvey> surveys;
				
        Session session = null;
    	Transaction transaction = null;
    	
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(PatientData.class);
	    ownerCriteria.setProjection(Property.forName("patientId"));
	    ownerCriteria.add(Restrictions.eq("patientId", id));
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("patientData").in(ownerCriteria));    		
    		
    		surveys = getAnsweredSurveysForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}

    	return surveys;
	}
	
	@Override
	public AnsweredSurvey getAnsweredSurvey(Long id) {
		AnsweredSurvey res;
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
    	}
    	
		return res;
	}

	@Override
	public Boolean addAnsweredSurvey(AnsweredSurvey as) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        PatientData patientData = patientDataDao.getPatientData(as.getPatientData().getPatientId());
        as.setPatientData(patientData);

        try{
           tx = session.beginTransaction();
           session.save(as); 
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
	public Boolean updateAnsweredSurvey(AnsweredSurvey as) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        PatientData patientData = patientDataDao.getPatientData(as.getPatientData().getPatientId());
        as.setPatientData(patientData);

        try{
           tx = session.beginTransaction();
           session.update(as); 
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
	public Boolean deleteAnsweredSurvey(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	
    	answerDao.deleteAnswersForAnsweredSurvey(id);
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		AnsweredSurvey s = getById(id);   		
    		
    		res = delete(s);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean deleteMultipleAnsweredSurveys(Long[] ids) {
        Boolean res = true;
        
    	for(Long id : ids){
        	if(deleteAnsweredSurvey(id)==false){
        		res=false;
        	}
   		}
    	
		return res;
	}

	@Override
	public void deleteAnsweredSurveysForPatient(Long id) {
		List<AnsweredSurvey> answeredSurveys = getAnsweredSurveysForPatient(id);
		for(AnsweredSurvey answeredSurvey : answeredSurveys) {
			deleteAnsweredSurvey(answeredSurvey.getAnsweredSurveyId());
		}
	}
}
