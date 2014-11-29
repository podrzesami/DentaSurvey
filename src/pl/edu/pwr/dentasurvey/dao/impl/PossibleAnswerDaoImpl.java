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
import pl.edu.pwr.dentasurvey.dao.PossibleAnswerDao;
import pl.edu.pwr.dentasurvey.dao.QuestionDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.PossibleAnswer;
import pl.edu.pwr.dentasurvey.objects.Question;

@Repository("possibleAnswerDao")
public class PossibleAnswerDaoImpl extends AbstractDao<PossibleAnswer> implements PossibleAnswerDao {
	private Log log = LogFactory.getLog(PossibleAnswer.class);
	
	@Autowired
	AnswerDao answerDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	public PossibleAnswerDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, PossibleAnswer.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<PossibleAnswer> getPossibleAnswersForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<PossibleAnswer> getAllPossibleAnswers() {
		List<PossibleAnswer> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getPossibleAnswersForCryteria(criteria);
 
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
	public List<PossibleAnswer> getPossibleAnswersForQuestion(Long questionId) {
		List<PossibleAnswer> res;
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
    		res = getPossibleAnswersForCryteria(criteria);
 
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
	public SearchResponse getPossibleAnswersForJqgrid(Long questionId, SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<PossibleAnswer> surveys;
		int totalSize;
		
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
    		totalSize = getPossibleAnswersForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc("possibleAnswer"));
    		} else {
    			criteria.addOrder(Order.desc("possibleAnswer"));
    		} 
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getPossibleAnswersForCryteria(criteria);
 
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
	public PossibleAnswer getPossibleAnswer(Long id) {
		PossibleAnswer res;
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
	public Boolean addPossibleAnswer(PossibleAnswer ans) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Question question = questionDao.getQuestion(ans.getQuestion().getQuestionId());
        ans.setQuestion(question);
        
        try{
           tx = session.beginTransaction();
           session.save(ans); 
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
	public Boolean updatePossibleAnswer(PossibleAnswer ans) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Question question = questionDao.getQuestion(ans.getQuestion().getQuestionId());
        ans.setQuestion(question);

        try{
           tx = session.beginTransaction();
           session.update(ans); 
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
	public Boolean deletePossibleAnswer(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
	 
    		PossibleAnswer ans = getById(id);    		
    		
    		res = delete(ans);
	 
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
	public Boolean deleteMultiplePossibleAnswers(Long[] ids) {
        Boolean res = true;
 
    	for(Long id : ids){
        	if(deletePossibleAnswer(id)==false){
        		res=false;
        	}
   		}
    	
		return res;
	}
}