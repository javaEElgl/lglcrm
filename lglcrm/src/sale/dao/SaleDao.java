package sale.dao;

import java.util.List;

import sale.pojo.Sale;


public interface SaleDao {
	public List<Sale> splitPage(int offset,int pageSize);   //��ҳ��ѯ
	public List<Sale> find();   		        //��ѯ����
	public List<Sale> find(Sale sale);   		//����HQL����ѯ
	public boolean add(Sale sale);				//���
	public boolean del(Sale sale);				//ɾ��
	public boolean update(Sale sale);           //�޸�
}
