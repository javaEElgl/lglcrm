package author.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import author.dao.UserDao;
import author.pojo.User;



@Transactional
public class UserDaoImpl implements UserDao {
	List<User> list = null;
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * 登陆
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public User login(String userName,String passWord){
		User u=null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", userName));
		criteria.add(Restrictions.eq("password", passWord));
		List l = criteria.list();
		if(l.size()==0){
			return null;
		}
		u=(User)l.get(0);
		return u;
	}

	@Override
	@Transactional
	public List<User> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User order by userId ASC");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List<User> list = query.list();
		return list;
	}

	@Transactional
	public List<User> find() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria qbc = session.createCriteria(User.class);
		List<User> list = qbc.list();
		return list;
	}

	@Transactional
	public List<User> find(User u) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username=?");
		query.setParameter(0, u.getUsername());
		return query.list();
	}

	public boolean add(User u) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(u);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}
	
	@Override
	public boolean del(User u) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(u);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	@Override
	//更新用户
	public boolean update(User u) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(u);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}
	
}
