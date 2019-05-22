package cust.dao;

import java.util.List;

import cust.pojo.Customer;


public interface CustomerDao {
		public List<Customer> splitPage(int offset,int pageSize); 
		public List<Customer> find();   
		public List<Customer> find(Customer c);
		public boolean add(Customer c);
		public boolean del(Customer c);
		public boolean update(Customer c);
		public List<Object[]> rept(String flag);
}
