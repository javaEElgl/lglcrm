package cust.dao;

import java.util.List;

import cust.pojo.Activity;
import cust.pojo.Customer;


public interface ActivityDao {
		public List<Activity> find();   
		public List<Activity> find(Customer c);
		public boolean add(Activity a);
		public boolean del(Activity a);
		public boolean update(Activity a);
}
