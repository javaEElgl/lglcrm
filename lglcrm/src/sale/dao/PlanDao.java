package sale.dao;

import java.util.List;

import sale.pojo.Plan;
import sale.pojo.Sale;


public interface PlanDao {
	public List<Plan> find();   				//��ѯ����
	public List<Plan> find(Sale s);   			//����Sale��Ų�ƻ�		
	public boolean add(Plan plan);				//���
	public boolean del(Plan plan);				//ɾ��
	public boolean update(Plan plan);           //�޸�
}
