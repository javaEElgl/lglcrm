package base.service;

import java.util.List;

import base.pojo.Dict;
import utils.page.Page;


public interface DictService {
	public Page splitPage(int pageIndex,int pageSize);
	public List<Dict> find();   				//��ѯ����
	public List<Dict> find(Dict d);   			//����HQL
	public boolean add(Dict d);  				//����
	public boolean del(Dict d);  				//ɾ��
	public boolean update(Dict d);  			//�޸�
}
