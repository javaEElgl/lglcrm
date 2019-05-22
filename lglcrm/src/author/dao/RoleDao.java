package author.dao;

import java.util.List;

import author.pojo.Role;



public interface RoleDao {
	public List<Role> splitPage(int offset,int pageSize);   //分页查询
	public Role find(int roleID); 
	public List<Role> find();  //查询所有
	public List<Role> find(Role role);  
	public boolean add(Role role);	 //增加角色 
	public boolean del(Role role);   //删除角色
	public boolean update(Role role); //修改角色
}
