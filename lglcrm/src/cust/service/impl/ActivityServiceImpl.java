package cust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cust.dao.ActivityDao;
import cust.pojo.Activity;
import cust.pojo.Customer;
import cust.service.ActivityService;



@Service(value="activityService")
public class ActivityServiceImpl implements ActivityService {
	@Resource(name="activityDao")
	private ActivityDao activityDao;
	
	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	
	
	@Override
	public List<Activity> find() {
		return activityDao.find();
	}

	@Override
	public List<Activity> find(Customer c) {
		return activityDao.find(c);
	}

	@Override
	public boolean add(Activity a) {
		return activityDao.add(a);
	}

	@Override
	public boolean del(Activity a) {
		return activityDao.del(a);
	}

	@Override
	public boolean update(Activity a) {
		return activityDao.update(a);
	}

}
