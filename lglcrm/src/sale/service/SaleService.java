package sale.service;

import java.util.List;

import author.pojo.User;
import sale.pojo.Sale;
import utils.page.Page;



public interface SaleService {
	public Page splitPage(int pageIndex,int pageSize);
	public List<Sale> find();   		        //默认查询所有
	public List<Sale> find(Sale sale);   		//HQL查询
	public boolean add(Sale sale);				//添加
	public boolean del(Sale sale);				//删除
	public boolean update(Sale sale);           //修改
	public List<User> find_User();
}
