package base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import base.dao.ProductDao;
import base.pojo.Product;

@Repository(value="productDao")
public class ProductDaoImpl implements ProductDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Product> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product order by ID");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Transactional
	public List<Product> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product order by ID");
		return query.list();
	}

	@Transactional
	public List<Product> find(Product p) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product p where p.name like '%"+p.getName()+"%' and p.storageName like '%"+p.getStorageName()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Product p) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(p);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

	@Override
	public boolean del(Product p) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.delete(p);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

	@Override
	public boolean update(Product p) {
		Session session = sessionFactory.openSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(p);
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			ts.rollback();
			return false;
		}
	}

}
