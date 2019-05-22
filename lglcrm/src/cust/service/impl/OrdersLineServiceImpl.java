package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.dao.ProductDao;
import base.pojo.Product;
import cust.dao.OrdersLineDao;
import cust.pojo.Orders;
import cust.pojo.OrdersLine;
import cust.service.OrdersLineService;

@Service(value="ordersLineService")
public class OrdersLineServiceImpl implements OrdersLineService {
	@Resource(name="ordersLineDao")
	private OrdersLineDao ordersLineDao;
	@Resource(name="productDao")
	private ProductDao productDao;
	
	public OrdersLineDao getOrdersLineDao() {
		return ordersLineDao;
	}

	public void setOrdersLineDao(OrdersLineDao ordersLineDao) {
		this.ordersLineDao = ordersLineDao;
	}
	
	
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<OrdersLine> find() {
		return ordersLineDao.find();
	}

	@Override
	public List<OrdersLine> find(Orders o) {
		return ordersLineDao.find(o);
	}

	@Override
	public boolean add(OrdersLine ol) {
		return ordersLineDao.add(ol);
	}

	@Override
	public boolean del(OrdersLine ol) {
		return ordersLineDao.del(ol);
	}

	@Override
	public boolean update(OrdersLine ol) {
		return ordersLineDao.update(ol);
	}

	@Override
	public List<Product> find_product() {
		return productDao.find();
	}

}
