package pl.edu.pwr.dentasurvey.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.dao.AnswerDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Answer;
import pl.edu.pwr.dentasurvey.objects.AnsweredSurvey;
import pl.edu.pwr.dentasurvey.objects.Question;

@Repository("answerDao")
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {
	private Log log = LogFactory.getLog(Answer.class);
	
	@Autowired
	public AnswerDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, Answer.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<Answer> getAnswersForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<Answer> getAllAnswers() {
		List<Answer> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getAnswersForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
	    return res;
	}
	
	@Override
	public List<Answer> getAnswersForAnsSurvey(Long ansSurveyId) {
		List<Answer> res;
        Session session = null;
    	Transaction transaction = null;
 
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(AnsweredSurvey.class);
	    ownerCriteria.setProjection(Property.forName("answeredSurveyId"));
	    ownerCriteria.add(Restrictions.eq("answeredSurveyId", ansSurveyId));
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("answeredSurvey").in(ownerCriteria));  
    		res = getAnswersForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
	    return res;
	}
	
	@Override
	public List<Answer> getAnswersForQuestion(Long questionId) {
		List<Answer> res;
        Session session = null;
    	Transaction transaction = null;
 
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Question.class);
	    ownerCriteria.setProjection(Property.forName("questionId"));
	    ownerCriteria.add(Restrictions.eq("questionId", questionId));
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("question").in(ownerCriteria));  
    		res = getAnswersForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
	    return res;
	}	

	@Override
	public SearchResponse getAnswersForJqgrid(Long surveyId, SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<Answer> surveys;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		totalSize = getAnswersForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		
    		// TODO: add sorting
    		
 /*  		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc("title"));
    		} else {
    			criteria.addOrder(Order.desc("title"));
    		} */
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getAnswersForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
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
	public Answer getAnswer(Long id) {
		Answer res;
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
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean addAnswer(Answer s) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		res = save(s);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean updateAnswer(Answer s) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		res = updateAnswer(s);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean deleteAnswer(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Answer s = getById(id);
    		
    		
    		
    		res = delete(s);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean deleteMultipleAnswers(Long[] ids) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		for(Long id : ids){
        		Answer s = getById(id);
        		res = delete(s);
    		}
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn�t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}
}
