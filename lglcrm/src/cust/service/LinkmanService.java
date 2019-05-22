package cust.service;

import java.util.List;

import cust.pojo.Customer;
import cust.pojo.Linkman;



public interface LinkmanService {
		public List<Linkman> find();   
		public List<Linkman> find(Customer c);
		public boolean add(Linkman l);
		public boolean del(Linkman l);
		public boolean update(Linkman l);
}
