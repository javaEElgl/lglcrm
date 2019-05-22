package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.LostDao;
import cust.pojo.Customer;
import cust.pojo.Lost;

@Repository(value="lostDao")
public class LostDaoImpl implements LostDao{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Lost> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Lost order by ID ASC");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Transactional
	public List<Lost> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Lost order by ID ASC");
		return query.list();
	}

	@Transactional
	public List<Lost> find(Lost l) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Lost l where l.customer like '%"+l.getCustomer()+"%' and l.manager like '%"+l.getManager()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Lost l) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(l);
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
	public boolean del(Lost l) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(l);
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
	public boolean update(Lost l) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(l);
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

	@Transactional
	public List<Lost> rept(String flag) {
		String sql = null;
		if(flag != null){
			 sql = "from Lost l where l.status = 3 and l.lostdate like '%"+flag+"%'";
		}else{
			sql = "from Lost l where l.status = 3";
		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		return query.list();
	}

}
