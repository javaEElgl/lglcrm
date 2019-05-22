package cust.dao;

import java.util.List;

import cust.pojo.Customer;
import cust.pojo.Orders;


public interface OrdersDao {
		public List<Orders> find();   
		public List<Orders> find(Customer c);
		public boolean add(Orders o);
		public boolean del(Orders o);
		public boolean update(Orders o);
		public List<Object[]> rept();
}
