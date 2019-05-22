package sale.dao;

import java.util.List;

import sale.pojo.Sale;


public interface SaleDao {
	public List<Sale> splitPage(int offset,int pageSize);   //分页查询
	public List<Sale> find();   		        //查询所有
	public List<Sale> find(Sale sale);   		//根据HQL语句查询
	public boolean add(Sale sale);				//添加
	public boolean del(Sale sale);				//删除
	public boolean update(Sale sale);           //修改
}
