package author.service;

import java.util.List;

import author.pojo.Role;
import utils.page.Page;


public interface RoleService {
	
	public Page splitPage(int pageIndex,int pageSize);
	public List<Role> find();  //查询所有
	public List<Role> find(Role role);  
	public boolean add(Role role);	 //增加角色 
	public boolean del(Role role);   //删除角色
	public boolean update(Role role); //修改角色
}
