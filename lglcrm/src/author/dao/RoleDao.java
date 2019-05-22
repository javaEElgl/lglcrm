package author.dao;

import java.util.List;

import author.pojo.Role;



public interface RoleDao {
	public List<Role> splitPage(int offset,int pageSize);   //��ҳ��ѯ
	public Role find(int roleID); 
	public List<Role> find();  //��ѯ����
	public List<Role> find(Role role);  
	public boolean add(Role role);	 //���ӽ�ɫ 
	public boolean del(Role role);   //ɾ����ɫ
	public boolean update(Role role); //�޸Ľ�ɫ
}
