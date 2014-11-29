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

import pl.edu.pwr.dentasurvey.dao.RoleDao;
import pl.edu.pwr.dentasurvey.dao.UserDao;
import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchRequest;
import pl.edu.pwr.dentasurvey.jqgrid.objects.SearchResponse;
import pl.edu.pwr.dentasurvey.objects.Role;
import pl.edu.pwr.dentasurvey.objects.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
	private Log log = LogFactory.getLog(User.class);
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	public UserDaoImpl(HibernateUtil hibernateUtil) {
		super(hibernateUtil, User.class);
	}
	
	@SuppressWarnings("unchecked")
	private List<User> getUsersForCryteria(Criteria cr) {
		return cr.list();
	}	
	
	@Override
	public SearchResponse getUsersForJqgrid(SearchRequest req) {
		SearchResponse resp = new SearchResponse();
		List<User> users;
		int totalSize;
		
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		totalSize = getUsersForCryteria(criteria).size();
    		criteria.setMaxResults(req.getRows());
    		if(req.getSord().equals("asc")) {
    			criteria.addOrder(Order.asc(req.getSidx()));
    		} else {
    			criteria.addOrder(Order.desc(req.getSidx()));
    		}
    		criteria.setFirstResult((req.getPage()-1)*(req.getRows()));
    		
    		users = getUsersForCryteria(criteria);
 
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
    	
    	resp.setRows(users);
    	resp.setPage(req.getPage());
    	resp.setRecords(totalSize);
    	resp.setSidx(req.getSidx());
    	resp.setSord(req.getSord());
    	resp.setTotal((totalSize+req.getRows()-1)/req.getRows());
    	
	    return resp;
	}	
	
	@Override
	public List<User> getAllUsers() {
		List<User> res;
        Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		Criteria criteria = createCriteria();
    		res = getUsersForCryteria(criteria);
 
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
	public User getUser(Long id) {
		User res;
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
	public Boolean addUser(User u) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Role role = roleDao.getRole(u.getRole().getRole());
        u.setRole(role);

        try{
           tx = session.beginTransaction();
           session.save(u); 
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
	public Boolean updateUser(User u) {
        Boolean res = false;
        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        Role role = roleDao.getRole(u.getRole().getRole());
        u.setRole(role);

        try{
           tx = session.beginTransaction();
           session.update(u); 
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
	public Boolean deleteUser(Long id) {
        Boolean res = false;
		Session session = null;
    	Transaction transaction = null;
 
    	try{
    		session = getSession();
    		transaction = session.beginTransaction();
    		transaction.setTimeout(5);
 
    		User u = getById(id);
    		res = delete(u);
 
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
	public Boolean deleteMultipleUsers(Long[] ids) {
        Boolean res = true;
        
    	for(Long id : ids){
        	if(deleteUser(id)==false){
        		res=false;
        	}
   		}
    	
		return res;
	}
}
