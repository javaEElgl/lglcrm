package author.dao;

import java.util.List;

import author.pojo.User;



public interface UserDao {
	public User login(String userName,String passWord);     		//��½����
	public List<User> splitPage(int offset,int pageSize);   //��ҳ��ѯ
	public List<User> find();                						//��ѯ����
	public List<User> find(User u);   	//ģ����ѯ
	public boolean add(User u);      	//���
	public boolean del(User u);      	//ɾ��
	public boolean update(User u);   	//�޸�
}
