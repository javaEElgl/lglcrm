package author.service.imp;

import java.util.ArrayList;
import java.util.List;

import utils.page.Page;
import author.dao.RoleDao;
import author.dao.RoleRightDao;
import author.dao.UserDao;
import author.pojo.Right;
import author.pojo.Role;
import author.pojo.Role_Right;
import author.pojo.User;
import author.service.UserService;



public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private RoleDao roleDao;
	private RoleRightDao rrDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public RoleRightDao getRrDao() {
		return rrDao;
	}

	public void setRrDao(RoleRightDao rrDao) {
		this.rrDao = rrDao;
	}

	
	
	@Override
	public User login(String userName, String passWord) {
		return userDao.login(userName, passWord);
	}

	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = userDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //��ȡ��ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
//		System.out.println(trueIndex);
		int offset = Page.getOffset(trueIndex, pageSize); //������ȷ��ҳ���ÿҳ��ʾ���������㿪ʼ�±�
//		System.out.println(offset);
		List<User> list = userDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
		return pb;
	}

	@Override
	public List<User> find() {
		return userDao.find();
	}
	
	@Override
	public List<User> find(User u) {
		return userDao.find(u);
	}
	
	@Override
	public boolean add(User u) {
		return userDao.add(u);
	}

	@Override
	public boolean del(User u) {
		return userDao.del(u);
	}

	@Override
	public boolean update(User u) {
		return userDao.update(u);
	}

	@Override
	public List<Role> find_Role() {
		return roleDao.find();
	}

	@Override
	public Role find_RoleByID(int roleID) {
		return roleDao.find(roleID);
	}

	@Override
	public List<Right> find_Right(int roleID) {
		List<Right> list = new ArrayList<Right>();
		List<Role_Right> rr = rrDao.find(roleID);
		for (int i = 0; i < rr.size(); i++) {
			int rightID = rr.get(i).getRightId();
			Right ri = rrDao.findRight(rightID);
			list.add(ri);
		}
		return list;
	}


}
