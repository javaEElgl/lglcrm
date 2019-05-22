package cust.dao;

import java.util.List;

import cust.pojo.Orders;
import cust.pojo.OrdersLine;


public interface OrdersLineDao {
		public List<OrdersLine> find();   
		public List<OrdersLine> find(Orders o);
		public boolean add(OrdersLine ol);
		public boolean del(OrdersLine ol);
		public boolean update(OrdersLine ol);
}
