package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.ActivityDao;
import cust.pojo.Activity;
import cust.pojo.Customer;

@Repository(value="activityDao")
public class ActivityDaoImpl implements ActivityDao{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Activity> find() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Activity.class);
		return cr.list();
	}

	@Transactional
	public List<Activity> find(Customer c) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Activity where customer.ID="+c.getID());
		return query.list();
	}

	@Override
	public boolean add(Activity a) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tc= session.beginTransaction();
		try {
			session.save(a);
			tc.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tc.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	@Override
	public boolean del(Activity a) {
		Session session = sessionFactory.openSession();
		Transaction tc= session.beginTransaction();
		try {
			session.delete(a);
			tc.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tc.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	@Override
	public boolean update(Activity a) {
		Session session = sessionFactory.openSession();
		Transaction tc= session.beginTransaction();
		try {
			session.update(a);
			tc.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tc.rollback();
			return false;
		}finally{
			session.close();
		}
	}

}
