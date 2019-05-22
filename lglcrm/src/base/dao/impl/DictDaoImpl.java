package base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.dao.DictDao;
import base.pojo.Dict;

@Repository(value="dictDao")
public class DictDaoImpl implements DictDao {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Dict> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dict order by ID");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Transactional
	public List<Dict> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dict order by ID");
		return query.list();
	}

	@Transactional
	public List<Dict> find(Dict d) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Dict d where d.type like '%"+d.getType()+"%' and d.item like '%"+d.getItem()+"%' and d.value like '%"+d.getValue()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Dict d) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(d);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

	@Override
	public boolean del(Dict d) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.delete(d);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

	@Override
	public boolean update(Dict d) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(d);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

}
