package sale.service;

import java.util.List;

import sale.pojo.Plan;
import sale.pojo.Sale;


public interface PlanService {
	public List<Plan> find();   				//��ѯ����
	public List<Plan> find(Sale s);   				
	public boolean add(Plan plan);				//���
	public boolean del(Plan plan);				//ɾ��
	public boolean update(Plan plan);           //�޸�
}
