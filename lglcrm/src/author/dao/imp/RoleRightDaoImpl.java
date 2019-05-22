package author.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import author.dao.RoleRightDao;
import author.pojo.Right;
import author.pojo.Role_Right;



public class RoleRightDaoImpl implements RoleRightDao {

	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//通过角色id获取角色权限集合
	@Transactional
	public List<Role_Right> find(int roleId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role_Right where roleId=?");
		query.setParameter(0, roleId);
		List<Role_Right> list = query.list();
		return list;
	}

	@Transactional
	//给角色添加权限
	public boolean add(Role_Right rr) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.save(rr);
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
	public boolean del(Role_Right rr) {
		Session session = sessionFactory.openSession();
		Transaction tc = session.beginTransaction();
		try {
			session.delete(rr);
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

	//通过权限id获取权限
	@Transactional
	public Right findRight(int rightID) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Right where rightId=?");
		query.setParameter(0, rightID);
		Right right = (Right) query.uniqueResult();
		return right;
	}

}
