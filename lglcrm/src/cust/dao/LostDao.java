package cust.dao;

import java.util.List;

import cust.pojo.Lost;


public interface LostDao {
		public List<Lost> splitPage(int offset,int pageSize);   //∑÷“≥≤È—Ø
		public List<Lost> find();   
		public List<Lost> find(Lost l);
		public boolean add(Lost l);
		public boolean del(Lost l);
		public boolean update(Lost l);
		public List<Lost> rept(String flag);
}
