package author.service;

import java.util.List;

import author.pojo.Role;
import utils.page.Page;


public interface RoleService {
	
	public Page splitPage(int pageIndex,int pageSize);
	public List<Role> find();  //��ѯ����
	public List<Role> find(Role role);  
	public boolean add(Role role);	 //���ӽ�ɫ 
	public boolean del(Role role);   //ɾ����ɫ
	public boolean update(Role role); //�޸Ľ�ɫ
}
