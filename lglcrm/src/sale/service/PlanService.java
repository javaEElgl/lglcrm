package sale.service;

import java.util.List;

import sale.pojo.Plan;
import sale.pojo.Sale;


public interface PlanService {
	public List<Plan> find();   				//查询所有
	public List<Plan> find(Sale s);   				
	public boolean add(Plan plan);				//添加
	public boolean del(Plan plan);				//删除
	public boolean update(Plan plan);           //修改
}
