package author.service.imp;

import java.util.List;

import author.dao.RoleRightDao;
import author.pojo.Role_Right;
import author.service.RoleRightService;



public class RoleRightServiceImpl implements RoleRightService {
	private RoleRightDao rrDao;
	
	public RoleRightDao getRrDao() {
		return rrDao;
	}

	public void setRrDao(RoleRightDao rrDao) {
		this.rrDao = rrDao;
	}

	@Override
	public List<Role_Right> find(int roleId) {
		return rrDao.find(roleId);
	}

	@Override
	public boolean add(Role_Right rr) {
		return rrDao.add(rr);
	}

	@Override
	public boolean del(Role_Right rr) {
		return rrDao.del(rr);
	}

}
