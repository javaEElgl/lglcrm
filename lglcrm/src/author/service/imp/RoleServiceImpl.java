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
		int allRow = roleDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Role> list = roleDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
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
