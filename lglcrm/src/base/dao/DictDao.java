package base.dao;

import java.util.List;

import base.pojo.Dict;



public interface DictDao {
	public List<Dict> splitPage(int offset,int pageSize);
	public List<Dict> find();   				//��ѯ����
	public List<Dict> find(Dict d);   		//����HQL
	public boolean add(Dict d);  				//����
	public boolean del(Dict d);  				//ɾ��
	public boolean update(Dict d);  			//�޸�
}
