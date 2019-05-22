package author.service;

import java.util.List;

import utils.page.Page;
import author.pojo.Right;
import author.pojo.Role;
import author.pojo.User;



public interface UserService {
	public User login(String userName,String passWord);     			//��½����
	public Page splitPage(int pageIndex,int pageSize);   	//��ҳ��ѯ
	public List<User> find();            //��ѯ����
	public List<User> find(User u);   	//ģ����ѯ
	public boolean add(User u);      	//���
	public boolean del(User u);      	//ɾ��
	public boolean update(User u);   	//�޸�
	public List<Role> find_Role();   	//��ѯ����Role
	public Role find_RoleByID(int roleID);
	public List<Right> find_Right(int roleID); //��Ȩ��
}
