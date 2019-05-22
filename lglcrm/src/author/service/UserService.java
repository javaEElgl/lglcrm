package author.service;

import java.util.List;

import utils.page.Page;
import author.pojo.Right;
import author.pojo.Role;
import author.pojo.User;



public interface UserService {
	public User login(String userName,String passWord);     			//登陆方法
	public Page splitPage(int pageIndex,int pageSize);   	//分页查询
	public List<User> find();            //查询所有
	public List<User> find(User u);   	//模糊查询
	public boolean add(User u);      	//添加
	public boolean del(User u);      	//删除
	public boolean update(User u);   	//修改
	public List<Role> find_Role();   	//查询所有Role
	public Role find_RoleByID(int roleID);
	public List<Right> find_Right(int roleID); //查权限
}
