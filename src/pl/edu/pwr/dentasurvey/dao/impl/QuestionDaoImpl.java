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
import pl.edu.pwr.dentasurvey.dao.QuestionCategoryDao;
import pl.edu.pwr.dentasurvey.dao.QuestionDao;
import pl.edu.pwr.dentasurvey.dao.QuestionTypeDao;
import pl.edu.pwr.dentasurvey.dao.SurveyDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Answer;
import pl.edu.pwr.dentasurvey.objects.Question;
import pl.edu.pwr.dentasurvey.objects.QuestionCategory;
import pl.edu.pwr.dentasurvey.objects.QuestionType;
import pl.edu.pwr.dentasurvey.objects.Survey;

@Repository("questionDao")
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {
	private Log log = LogFactory.getLog(Question.class);
	
	@Autowired
	AnswerDao answerDao;
	
	@Autowired
	QuestionCategoryDao questionCategoryDao;
	
	@Autowired
	QuestionTypeDao questionTypeDao;
	
	@Autowired
	SurveyDao surveyDao;
	
	@Autowired
	public QuestionDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, Question.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<Question> getQuestionsForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<Question> getAllQuestions() {
		List<Question> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getQuestionsForCryteria(criteria);
 
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
	public List<Question> getQuestionsForSurvey(Long surveyId) {
		List<Question> res;
        Session session = null;
    	Transaction transaction = null;
 
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Survey.class);
	    ownerCriteria.setProjection(Property.forName("surveyId"));
	    ownerCriteria.add(Restrictions.eq("surveyId", surveyId));
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("survey").in(ownerCriteria));  
    		res = getQuestionsForCryteria(criteria);
 
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
	public SearchResponse getQuestionsForJqgrid(Long surveyId, SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<Question> surveys;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Survey.class);
	    ownerCriteria.setProjection(Property.forName("surveyId"));
	    ownerCriteria.add(Restrictions.eq("surveyId", surveyId));
	    
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("survey").in(ownerCriteria)); 
    		totalSize = getQuestionsForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc("question"));
    		} else {
    			criteria.addOrder(Order.desc("question"));
    		} 
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getQuestionsForCryteria(criteria);
 
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
	public Question getQuestion(Long id) {
		Question res;
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
	public Boolean addQuestion(Question q) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        QuestionCategory category = questionCategoryDao
        		.getQuestionCategory(q.getQuestionCategory().getCategory());
        QuestionType type = questionTypeDao.getQuestionType(q.getQuestionType().getType());
        Survey survey = surveyDao.getSurvey(q.getSurvey().getSurveyId());
        q.setQuestionType(type);
        q.setQuestionCategory(category);
        q.setSurvey(survey);
        
        try{
           tx = session.beginTransaction();
           session.save(q); 
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
	public Boolean updateQuestion(Question q) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        QuestionCategory category = questionCategoryDao
        		.getQuestionCategory(q.getQuestionCategory().getCategory());
        QuestionType type = questionTypeDao.getQuestionType(q.getQuestionType().getType());
        Survey survey = surveyDao.getSurvey(q.getSurvey().getSurveyId());
        q.setQuestionType(type);
        q.setQuestionCategory(category);
        q.setSurvey(survey);
        
		List<Answer> answers = answerDao.getAnswersForQuestion(q.getQuestionId());
	    if(answers==null || answers.isEmpty()) {
	        try{
	           tx = session.beginTransaction();
	           session.update(q); 
	           tx.commit();
	        }catch (HibernateException e) {
	           if (tx!=null) tx.rollback();
	           e.printStackTrace(); 
	        }finally {
	           session.close(); 
	        }
	    } else {
	        try{
		           tx = session.beginTransaction();
		           Question old = getQuestion(q.getQuestionId());
		           old.setSurvey(null);
		           q.setQuestionId(null);
		           session.update(old); 
		           session.save(q);
		           
		           tx.commit();
		        }catch (HibernateException e) {
		           if (tx!=null) tx.rollback();
		           e.printStackTrace(); 
		        }finally {
		           session.close(); 
		        }
	    }
	    
		return res;

	}

	@Override
	public Boolean deleteQuestion(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	Question q = getQuestion(id);  
		List<Answer> answers = answerDao.getAnswersForQuestion(id);
		
	    if(answers==null || answers.isEmpty()) {   
	    	try{
	    		session = getSession();
	    		transaction = session.beginTransaction();
	    		transaction.setTimeout(5);	    		
	    		
	    		res = delete(q);
	 
	    		transaction.commit(); 
	    	}catch(RuntimeException e){
	    		try{
	    			transaction.rollback();
	    		}catch(RuntimeException rbe){
	    			log.error("Couldn’t roll back transaction");
	    		}
	    		throw e;
	    	}
	    } else {
	    	try{
	    		session = getSession();
	    		transaction = session.beginTransaction();
	    		transaction.setTimeout(5);
	    		
	    		q.setSurvey(null);
	    		session.update(q);
	 
	    		transaction.commit(); 
	    	}catch(RuntimeException e){
	    		try{
	    			transaction.rollback();
	    		}catch(RuntimeException rbe){
	    			log.error("Couldn’t roll back transaction");
	    		}
	    		throw e;
	    	}	    	
	    }
	    
		return res;
	}
	
	@Override
	public Boolean deleteQuestionFromAnswer(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
    	Question q = getQuestion(id);  
		List<Answer> answers = answerDao.getAnswersForQuestion(id);
		
	    if((answers==null || answers.isEmpty()) && q.getSurvey()==null) {   
	    	try{
	    		session = getSession();
	    		transaction = session.beginTransaction();
	    		transaction.setTimeout(5);	    		
	    		
	    		res = delete(q);
	 
	    		transaction.commit(); 
	    	}catch(RuntimeException e){
	    		try{
	    			transaction.rollback();
	    		}catch(RuntimeException rbe){
	    			log.error("Couldn’t roll back transaction");
	    		}
	    		throw e;
	    	}
	    } else {
	    	try{
	    		session = getSession();
	    		transaction = session.beginTransaction();
	    		transaction.setTimeout(5);
	    		
	    		q.setSurvey(null);
	    		session.update(q);
	 
	    		transaction.commit(); 
	    	}catch(RuntimeException e){
	    		try{
	    			transaction.rollback();
	    		}catch(RuntimeException rbe){
	    			log.error("Couldn’t roll back transaction");
	    		}
	    		throw e;
	    	}	    	
	    }
	    
		return res;
	}

	@Override
	public Boolean deleteMultipleQuestions(Long[] ids) {
        Boolean res = true;
 
    	for(Long id : ids){
        	if(deleteQuestion(id)==false){
        		res=false;
        	}
   		}
    	
		return res;
	}

	@Override
	public Question getQestionToAnswer(Long surveyId, int page) {
		List<Question> questions;
		
        Session session = null;
    	Transaction transaction = null;
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Survey.class);
	    ownerCriteria.setProjection(Property.forName("surveyId"));
	    ownerCriteria.add(Restrictions.eq("surveyId", surveyId));
	    
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("survey").in(ownerCriteria)); 
    		criteria.setMaxResults(1);
    		criteria.createAlias("questionType", "questionType");
    		
    		criteria.addOrder(Order.asc("questionType.type"));
    		
    		criteria.setFirstResult(page-1);
    		
    		questions = getQuestionsForCryteria(criteria);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
  	
    	if(questions == null || questions.size()==0) {
    		return null;
    	}
	    return questions.get(0);
	}
}