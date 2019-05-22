package base.dao;

import java.util.List;

import base.pojo.Dict;



public interface DictDao {
	public List<Dict> splitPage(int offset,int pageSize);
	public List<Dict> find();   				//查询所有
	public List<Dict> find(Dict d);   		//根据HQL
	public boolean add(Dict d);  				//增加
	public boolean del(Dict d);  				//删除
	public boolean update(Dict d);  			//修改
}
