package sale.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import author.pojo.User;
import sale.dao.SaleDao;
import sale.pojo.Sale;

@Repository(value="saleDao")
public class SaleDaoImpl implements SaleDao {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Sale> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Sale order by id ASC");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<Sale> list = query.list();
		return list;
	}

	@Transactional
	public List<Sale> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Sale");
		return query.list();
	}

	@Transactional
	//根据条件查询
	public List<Sale> find(Sale sale) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Sale s where s.name like '%"+sale.getName()+"%' and s.title like '%"+sale.getTitle()+"%'");
		return query.list();
	}

	@Override
	public boolean add(Sale sale) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(sale);
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
	public boolean del(Sale sale) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(sale);
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
	public boolean update(Sale sale) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(sale);
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
