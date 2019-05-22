package cust.service;

import java.util.List;

import base.pojo.Dict;
import author.pojo.User;
import cust.pojo.Customer;
import utils.page.Page;


public interface CustomerService {
		public Page splitPage(int pageIndex,int pageSize);
		public List<Customer> find();   
		public List<Customer> find(Customer c);
		public boolean add(Customer c);
		public boolean del(Customer c);
		public boolean update(Customer c);
		public List<User> find_user();
		public List<Dict> find_dict();
		public List<Object[]> rept(String flag);   
}
