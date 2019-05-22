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
import cust.dao.ServicerDao;
import cust.pojo.Customer;
import cust.pojo.Servicer;
import cust.service.ServicerService;

@Service(value="servicerService")
public class ServicerServiceImpl implements ServicerService {
	@Resource(name="servicerDao")
	private ServicerDao servicerDao;
	@Resource(name="customerDao")
	private CustomerDao customerDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="dictDao")
	private DictDao dictDao;
	
	public ServicerDao getServicerDao() {
		return servicerDao;
	}

	public void setServicerDao(ServicerDao servicerDao) {
		this.servicerDao = servicerDao;
	}
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
		int allRow = servicerDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Servicer> list = servicerDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
		return pb;
	}

	@Override
	public List<Servicer> find() {
		return servicerDao.find();
	}

	@Override
	public List<Servicer> find(Servicer sv) {
		return servicerDao.find(sv);
	}

	@Override
	public boolean add(Servicer sv) {
		return servicerDao.add(sv);
	}

	@Override
	public boolean del(Servicer sv) {
		return servicerDao.del(sv);
	}

	@Override
	public boolean update(Servicer sv) {
		return servicerDao.update(sv);
	}

	@Override
	public List<Dict> find_dict() {
		return dictDao.find();
	}

	@Override
	public List<User> find_user() {
		return userDao.find();
	}

	@Override
	public List<Customer> find_cust() {
		return customerDao.find();
	}
	
	@Override
	public List<Object[]> rept(int flag) {
		return servicerDao.rept(flag);
	}


}
