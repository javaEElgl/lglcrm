package author.dao.imp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import author.dao.RoleDao;
import author.pojo.Role;

public class RoleDaoImpl implements RoleDao {

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Role> splitPage(int offset, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role order by roleId ASC");
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Transactional(readOnly=true)
	public Role find(int roleID) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role where roleId=?");
		query.setParameter(0, roleID);
		Role role =(Role)query.uniqueResult();
		return role;
	}

	@Transactional
	public List<Role> find() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role");
		List<Role> list = query.list();
		return list;
	}

	@Transactional
	public List<Role> find(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role where roleName=?");
		query.setParameter(0, role.getRoleName());
		return query.list();
	}

	@Override
	public boolean add(Role role) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(role);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return false;
		}finally{
			session.clear();
		}
	}

	@Override
	//删除角色，需要先删除权限，再删除角色
	public boolean del(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.createSQLQuery("delete from sys_role_right where rr_role_id="+role.getRoleId()).executeUpdate();
			session.delete(role);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}

	@Override
	public boolean update(Role role) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(role);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			return false;
		}finally{
			session.close();
		}
	}

}
