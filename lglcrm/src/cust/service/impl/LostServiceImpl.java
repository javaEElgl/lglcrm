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
		int allRow = lostDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Lost> list = lostDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
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
