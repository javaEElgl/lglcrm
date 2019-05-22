package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.CustomerDao;
import cust.pojo.Customer;

@Repository(value="customerDao")
public class CustomerDaoImpl implements CustomerDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional
	public List<Customer> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.setFirstResult(offset);
		cr.setMaxResults(pageSize);
		return cr.list();
	}

	@Transactional
	public List<Customer> find() {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Customer.class);
		return cr.list();
	}

	@Transactional
	public List<Customer> find(Customer c) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer c where c.no like '%"+c.getNo()+"%' and c.name like '%"+c.getName()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Customer c) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(c);
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
	public boolean del(Customer c) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(c);
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
	public boolean update(Customer c) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(c);
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
	public List<Object[]> rept(String flag) {
		List<Object[]> list = null;
		String sql = null;
		if(flag.equals("level")){
			 sql = "select cst_level,count(cst_level) from cst_customer group by cst_level";
		}
		if(flag.equals("region")){
			 sql = "select cst_region,count(cst_region) from cst_customer group by cst_region";
		}
		Session session =sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			list = session.createSQLQuery(sql).list();
			ts.commit();
		} catch (Exception e) {
			ts.rollback();
			return list;
		} finally{
			session.close();
		}
		return list;
	}

}
