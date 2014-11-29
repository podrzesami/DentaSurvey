package pl.edu.pwr.dentasurvey.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.dao.AnsweredSurveyDao;
import pl.edu.pwr.dentasurvey.dao.PatientDataDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PatientData;

@Repository("patientDataDao")
public class PatientDataDaoImpl extends AbstractDao<PatientData> implements PatientDataDao {
	private Log log = LogFactory.getLog(PatientData.class);
	
	@Autowired
	AnsweredSurveyDao answeredSurveyDao;
	
	@Autowired
	public PatientDataDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, PatientData.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<PatientData> getPatientsDataForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<PatientData> getDataForAllPatients() {
		List<PatientData> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getPatientsDataForCryteria(criteria);
 
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
	public SearchResponse getPatientsForJqgrid(SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<PatientData> patients;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		totalSize = getPatientsDataForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc(req.getSidx()));
    		} else {
    			criteria.addOrder(Order.desc(req.getSidx()));
    		}
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		patients = getPatientsDataForCryteria(criteria);
 
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
    	
    	resp.setRows(patients);
    	resp.setPage(req.getPage());
    	resp.setRecords(totalSize);
    	resp.setSidx(req.getSidx());
    	resp.setSord(req.getSord());
    	resp.setTotal((totalSize+req.getRows()-1)/req.getRows());
    	
	    return resp;
	}	
	@Override
	public PatientData getPatientData(Long id) {
		PatientData res;
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
	public Boolean addPatientData(PatientData p) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try{
           tx = session.beginTransaction();
           session.save(p); 
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
	public Boolean updatePatientData(PatientData p) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try{
           tx = session.beginTransaction();
           session.update(p); 
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
	public Boolean updateOrSavePatientData(PatientData p) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;

        try{
           tx = session.beginTransaction();
           session.saveOrUpdate(p); 
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
	public Boolean deletePatientData(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	
    	answeredSurveyDao.deleteAnsweredSurveysForPatient(id);
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		PatientData s = getById(id);
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
	public Boolean deleteMultiplePatientData(Long[] ids) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		for(Long id : ids){
        		PatientData s = getById(id);
        		res = delete(s);
    		}
 
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
}
