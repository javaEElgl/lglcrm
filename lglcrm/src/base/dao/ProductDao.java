package base.dao;

import java.util.List;

import base.pojo.Product;


public interface ProductDao {
	public List<Product> splitPage(int offset,int pageSize);   //分页查询
	public List<Product> find();                //查询所有
	public List<Product> find(Product p);   	//模糊查询
	public boolean add(Product p);      	//添加
	public boolean del(Product p);      	//删除
	public boolean update(Product p);   	//修改
}
