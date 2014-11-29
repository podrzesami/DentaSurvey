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

import pl.edu.pwr.dentasurvey.dao.QuestionCategoryDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.objects.QuestionCategory;

@Repository("questionCategoryDao")
public class QuestionCategoryDaoImpl extends AbstractDao<QuestionCategory> implements QuestionCategoryDao {
	private Log log = LogFactory.getLog(QuestionCategory.class);
	
	@Autowired
	public QuestionCategoryDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, QuestionCategory.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<QuestionCategory> getQuestionCategorysForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public List<QuestionCategory> getAllQuestionCategory() {
		List<QuestionCategory> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getQuestionCategorysForCryteria(criteria);
 
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
	public QuestionCategory getQuestionCategory(Long id) {
		QuestionCategory res;
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
	public QuestionCategory getQuestionCategory(String category) {
		List<QuestionCategory> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		criteria.add(Property.forName("category").eq(category));    
    		res = getQuestionCategorysForCryteria(criteria);
 
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
