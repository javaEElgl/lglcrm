package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.dao.DictDao;
import base.pojo.Dict;
import utils.page.Page;
import author.dao.UserDao;
import author.pojo.User;
import cust.dao.CustomerDao;
import cust.pojo.Customer;
import cust.service.CustomerService;


@Service(value="customerService")
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="dictDao")
	private DictDao dictDao;
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public DictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}
	
	

	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = customerDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Customer> list = customerDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
		return pb;
	}

	@Override
	public List<Customer> find() {
		return customerDao.find();
	}

	@Override
	public List<Customer> find(Customer c) {
		return customerDao.find(c);
	}

	@Override
	public boolean add(Customer c) {
		return customerDao.add(c);
	}

	@Override
	public boolean del(Customer c) {
		return customerDao.del(c);
	}

	@Override
	public boolean update(Customer c) {
		return customerDao.update(c);
	}

	@Override
	public List<User> find_user() {
		return userDao.find();
	}

	@Override
	public List<Dict> find_dict() {
		return dictDao.find();
	}

	@Override
	public List<Object[]> rept(String flag) {
		
		return customerDao.rept(flag);
	}


}
