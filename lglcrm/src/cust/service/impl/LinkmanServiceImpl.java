package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cust.dao.LinkmanDao;
import cust.pojo.Customer;
import cust.pojo.Linkman;
import cust.service.LinkmanService;


@Service(value="linkmanService")
public class LinkmanServiceImpl implements LinkmanService {
	@Resource(name="linkmanDao")
	private LinkmanDao linkmanDao;
	
	public LinkmanDao getLinkmanDao() {
		return linkmanDao;
	}

	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	
	@Override
	public List<Linkman> find() {
		return linkmanDao.find();
	}

	@Override
	public List<Linkman> find(Customer c) {
		return linkmanDao.find(c);
	}

	@Override
	public boolean add(Linkman l) {
		return linkmanDao.add(l);
	}

	@Override
	public boolean del(Linkman l) {
		return linkmanDao.del(l);
	}

	@Override
	public boolean update(Linkman l) {
		return linkmanDao.update(l);
	}

}
