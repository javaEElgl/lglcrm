package cust.service;

import java.util.List;

import base.pojo.Dict;
import author.pojo.User;
import cust.pojo.Customer;
import cust.pojo.Servicer;
import utils.page.Page;


public interface ServicerService {
		public Page splitPage(int pageIndex,int pageSize);
		public List<Servicer> find();   
		public List<Servicer> find(Servicer sv);
		public boolean add(Servicer sv);
		public boolean del(Servicer sv);
		public boolean update(Servicer sv);
		public List<Dict> find_dict();
		public List<User> find_user();
		public List<Customer> find_cust();
		public List<Object[]> rept(int flag);  //统计报表
}
