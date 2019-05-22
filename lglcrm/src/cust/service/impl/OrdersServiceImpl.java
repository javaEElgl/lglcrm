package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cust.dao.OrdersDao;
import cust.pojo.Customer;
import cust.pojo.Orders;
import cust.service.OrdersService;

@Service(value="ordersService")
public class OrdersServiceImpl implements OrdersService {
	@Resource(name="ordersDao")
	private OrdersDao ordersDao;
	
	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	
	
	@Override
	public List<Orders> find() {
		return ordersDao.find();
	}

	@Override
	public List<Orders> find(Customer c) {
		return ordersDao.find(c);
	}

	@Override
	public boolean add(Orders o) {
		return ordersDao.add(o);
	}

	@Override
	public boolean del(Orders o) {
		return ordersDao.del(o);
	}

	@Override
	public boolean update(Orders o) {
		return ordersDao.update(o);
	}

	@Override
	public List<Object[]> rept() {
		return ordersDao.rept();
	}

}
