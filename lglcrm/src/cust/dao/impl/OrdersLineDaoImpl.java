package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.OrdersLineDao;
import cust.pojo.Orders;
import cust.pojo.OrdersLine;

@Repository(value="ordersLineDao")
public class OrdersLineDaoImpl implements OrdersLineDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<OrdersLine> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from OrdersLine order by ID");
		return query.list(); 
	}

	@Transactional
	public List<OrdersLine> find(Orders o) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from OrdersLine ol where ol.orders = '"+o.getID()+"'");
		return query.list();
	}

	@Override
	public boolean add(OrdersLine ol) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(ol);
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
	public boolean del(OrdersLine ol) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(ol);
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
	public boolean update(OrdersLine ol) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(ol);
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
