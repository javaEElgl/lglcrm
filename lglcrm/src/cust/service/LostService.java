package cust.service;

import java.util.List;

import cust.pojo.Customer;
import cust.pojo.Lost;
import utils.page.Page;



public interface LostService {
		public Page splitPage(int pageIndex,int pageSize);
		public List<Lost> find();   
		public List<Lost> find(Lost l);
		public boolean add(Lost l);
		public boolean del(Lost l);
		public boolean update(Lost l);
		public List<Customer> find_customer();
		public List<Lost> rept(String flag);
}
