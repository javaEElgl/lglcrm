package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import utils.page.Page;
import cust.dao.CustomerDao;
import cust.dao.LostDao;
import cust.pojo.Customer;
import cust.pojo.Lost;
import cust.service.LostService;

@Service(value="lostService")
public class LostServiceImpl implements LostService {
	@Resource(name="lostDao")
	private LostDao lostDao;
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	
	public LostDao getLostDao() {
		return lostDao;
	}
	public void setLostDao(LostDao lostDao) {
		this.lostDao = lostDao;
	}
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	
	
	@Override
	public List<Lost> find() {
		return lostDao.find();
	}

	@Override
	public List<Lost> find(Lost l) {
		return lostDao.find(l);
	}

	@Override
	public boolean add(Lost l) {
		return lostDao.add(l);
	}

	@Override
	public boolean del(Lost l) {
		return lostDao.del(l);
	}

	@Override
	public boolean update(Lost l) {
		return lostDao.update(l);
	}

	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = lostDao.find().size();   //总记录
		int lenght = Page.getLenght(allRow, pageSize);  //页数
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //获取正确的页码值
		int offset = Page.getOffset(trueIndex, pageSize); //开始下标
		List<Lost> list = lostDao.splitPage(offset, pageSize);  //得到返回 的数据集合
		Page pb = new Page();  //新建一个页面类
		pb.setCount(allRow);    //封装总条数
		pb.setPages(lenght);	//封装页数
		pb.setPageIndex(trueIndex);  //封装当前页码
		pb.setPageSize(pageSize);    //封装页面大小
		pb.setList(list);      //封装数据
		return pb;
	}

	@Override
	public List<Customer> find_customer() {
		return customerDao.find();
	}
	@Override
	public List<Lost> rept(String flag) {
		return lostDao.rept(flag);
	}

}
