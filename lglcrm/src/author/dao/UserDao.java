package author.dao;

import java.util.List;

import author.pojo.User;



public interface UserDao {
	public User login(String userName,String passWord);     		//登陆方法
	public List<User> splitPage(int offset,int pageSize);   //分页查询
	public List<User> find();                						//查询所有
	public List<User> find(User u);   	//模糊查询
	public boolean add(User u);      	//添加
	public boolean del(User u);      	//删除
	public boolean update(User u);   	//修改
}
