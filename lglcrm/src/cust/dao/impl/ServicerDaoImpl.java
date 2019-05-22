package cust.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cust.dao.ServicerDao;
import cust.pojo.Servicer;

@Repository(value="servicerDao")
public class ServicerDaoImpl implements ServicerDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Servicer> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Servicer order by ID");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Transactional
	public List<Servicer> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Servicer");
		return query.list();
	}

	@Transactional
	public List<Servicer> find(Servicer sv) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Servicer c where c.custname like '%"+sv.getCustname()+"%' and c.type like '%"+sv.getType()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Servicer sv) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(sv);
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
	public boolean del(Servicer sv) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(sv);
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
	public boolean update(Servicer sv) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(sv);
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
	public List<Object[]> rept(int flag) {
		List<Object[]> list = null;
		String sql = null;
		if(flag != 1){
			sql = "select sv_type,count(0) from cst_service where sv_status like '%"+flag+"%' group by sv_type";
		}else{
			sql = "select sv_type,count(0) from cst_service group by sv_type";
		}
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			list=session.createSQLQuery(sql).list();
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
		}finally{
			session.close();
		}
		return list;
	}

}
