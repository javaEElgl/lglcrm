package sale.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sale.dao.PlanDao;
import sale.pojo.Plan;
import sale.pojo.Sale;


@Repository(value="planDao")
public class PlanDaoImpl implements PlanDao {
	
	@Resource(name="sessionFactory")
	public SessionFactory sessionFactory;
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Plan> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Plan");
		return query.list();
	}

	@Transactional
	public List<Plan> find(Sale s) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Plan p where p.sale.id ="+s.getId());
		return query.list();
	}

	@Override
	public boolean add(Plan plan) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(plan);
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
	public boolean del(Plan plan) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(plan);
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
	public boolean update(Plan plan) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.update(plan);
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
