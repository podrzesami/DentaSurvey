package pl.edu.pwr.dentasurvey.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.pwr.dentasurvey.hibernate.HibernateUtil;

public abstract class AbstractDao<T> {

	private HibernateUtil hibernateUtil;
	private Class<T> ob;


	public AbstractDao(HibernateUtil hibernateUtil, Class<T> ob) {
		this.hibernateUtil = hibernateUtil;
		this.ob = ob;
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) hibernateUtil.getSession().get(ob, id);	
	}

	protected Criteria createCriteria() {
		return hibernateUtil.getSession().createCriteria(ob);
	}

	protected Query createQuery(String queryString) {
		return hibernateUtil.getSession().createQuery(queryString);

	}

	public Boolean save(T object) {
		hibernateUtil.getSession().save(object);
		return true;
	}

	public Boolean saveOrUpdate(T object) {
		hibernateUtil.getSession().saveOrUpdate(object);
		return true;
	}

	public Boolean update(T object) {
		hibernateUtil.getSession().update(object);
		return true;
	}

	public Boolean delete(T object) {
		hibernateUtil.getSession().delete(object);
		return true;
	}
	
	public Session getSession() {  
		return hibernateUtil.getSession();
	}
	
	public Transaction beginTransaction() {
		return hibernateUtil.getSession().beginTransaction();
	}	
}
