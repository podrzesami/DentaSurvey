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
import pl.edu.pwr.dentasurvey.dao.QuestionDao;
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
	QuestionDao questionDao;
	
	@Autowired
	AnsweredSurveyDao answeredSurveyDao;
	
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
    			log.error("Couldn’t roll back transaction");
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
    			log.error("Couldn’t roll back transaction");
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
    			log.error("Couldn’t roll back transaction");
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
    	
		DetachedCriteria ownerCriteria = DetachedCriteria.forClass(AnsweredSurvey.class);
	    ownerCriteria.setProjection(Property.forName("answeredSurveyId"));
	    ownerCriteria.add(Restrictions.eq("answeredSurveyId", surveyId));
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("answeredSurvey").in(ownerCriteria)); 
    		totalSize = getAnswersForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());

    		criteria.createAlias("question", "question");
    		criteria.createAlias("question.questionCategory", "category");
    		
    		
    		if(req.getSord().equals("asc")) {    			
    			criteria.addOrder(Order.asc(req.getSidx()));
    		} else {
    			criteria.addOrder(Order.desc(req.getSidx()));
    		} 
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		surveys = getAnswersForCryteria(criteria);
 
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
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public Boolean addAnswer(Answer a) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction transaction = null; 
        Question q = questionDao.getQuestion(a.getQuestion().getQuestionId());
        a.setQuestion(q);
        AnsweredSurvey ans = answeredSurveyDao.getAnsweredSurvey(a.getAnsweredSurvey().getAnsweredSurveyId());
        a.setAnsweredSurvey(ans);
        
        try{
            transaction = session.beginTransaction();
            session.save(a); 
            transaction.commit();
         }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace(); 
         }finally {
            session.close(); 
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
    			log.error("Couldn’t roll back transaction");
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
    	
    	Answer a = getAnswer(id);
    	Long questionId = a.getQuestion().getQuestionId();
    	
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		res = delete(a);
 
    		transaction.commit(); 
    	}catch(RuntimeException e){
    		try{
    			transaction.rollback();
    		}catch(RuntimeException rbe){
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
    	
    	questionDao.deleteQuestionFromAnswer(questionId);
    	
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
    			log.error("Couldn’t roll back transaction");
    		}
    		throw e;
    	}
    	
		return res;
	}

	@Override
	public void deleteAnswersForAnsweredSurvey(Long id) {
		List<Answer> answers = getAnswersForAnsSurvey(id);
		for(Answer answer : answers) {
			deleteAnswer(answer.getAnswerId());
		}
	}
}
