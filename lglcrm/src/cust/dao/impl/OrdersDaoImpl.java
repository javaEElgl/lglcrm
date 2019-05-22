package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.OrdersDao;
import cust.pojo.Customer;
import cust.pojo.Orders;

@Repository(value="ordersDao")
public class OrdersDaoImpl implements OrdersDao {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Orders> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Orders");
		return query.list();
	}

	@Transactional
	public List<Orders> find(Customer c) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Orders o where o.customer = '"+c.getID()+"'");
		return query.list();
	}

	@Override
	public boolean add(Orders o) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(o);
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
	public boolean del(Orders o) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(o);
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
	public boolean update(Orders o) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(o);
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
	public List<Object[]> rept() {
		String sql = "select c.cst_name,o.money from cst_customer c,(select or_cst_id,sum(or_money)money from orders group by or_cst_id)o where c.cst_id=o.or_cst_id";
		List<Object[]> list = null;
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			list=session.createSQLQuery(sql).list();
			ts.commit();
		} catch (Exception e) {
			ts.rollback();
		}
		return list;
	}

}
