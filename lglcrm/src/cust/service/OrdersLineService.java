package cust.service;

import java.util.List;

import base.pojo.Product;
import cust.pojo.Orders;
import cust.pojo.OrdersLine;


public interface OrdersLineService {
		public List<OrdersLine> find();   
		public List<OrdersLine> find(Orders o);
		public boolean add(OrdersLine ol);
		public boolean del(OrdersLine ol);
		public boolean update(OrdersLine ol);
		public List<Product> find_product();
}
