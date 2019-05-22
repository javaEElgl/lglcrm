package author.service.imp;

import java.util.List;

import utils.page.Page;
import author.dao.RoleDao;
import author.pojo.Role;
import author.service.RoleService;


public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	
	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = roleDao.find().size();   //总记录
		int lenght = Page.getLenght(allRow, pageSize);  //页数
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //获取正确的页码值
		int offset = Page.getOffset(trueIndex, pageSize); //开始下标
		List<Role> list = roleDao.splitPage(offset, pageSize);  //得到返回 的数据集合
		Page pb = new Page();  //新建一个页面类
		pb.setCount(allRow);    //封装总条数
		pb.setPages(lenght);	//封装页数
		pb.setPageIndex(trueIndex);  //封装当前页码
		pb.setPageSize(pageSize);    //封装页面大小
		pb.setList(list);      //封装数据
		return pb;
	}
	
	@Override
	public List<Role> find() {
		return roleDao.find();
	}

	@Override
	public List<Role> find(Role role) {
		return roleDao.find(role);
	}

	@Override
	public boolean add(Role role) {
		return roleDao.add(role);
	}

	@Override
	public boolean del(Role role) {
		return roleDao.del(role);
	}

	@Override
	public boolean update(Role role) {
		return roleDao.update(role);
	}


}
