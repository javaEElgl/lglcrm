package author.service;

import java.util.List;

import author.pojo.Role_Right;


public interface RoleRightService {
	 public List<Role_Right> find(int roleId);   //���ݽ�ɫID��ѯ��Ӧ������Ȩ��
	 public boolean add(Role_Right rr);			 //���ӽ�ɫȨ��	
	 public boolean del(Role_Right rr);			//ɾ����ɫȨ��
}
