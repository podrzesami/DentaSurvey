package pl.edu.pwr.dentasurvey.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.dentasurvey.dao.QuestionTypeDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.objects.QuestionType;

@Repository("questionTypeDao")
public class QuestionTypeDaoImpl extends AbstractDao<QuestionType> implements QuestionTypeDao {
	private Log log = LogFactory.getLog(QuestionType.class);
	
	@Autowired
	public QuestionTypeDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, QuestionType.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<QuestionType> getQuestionTypesForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<QuestionType> getAllQuestionTypes() {
		List<QuestionType> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getQuestionTypesForCryteria(criteria);
 
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
	public QuestionType getQuestionType(Long id) {
		QuestionType res;
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
}
