package sale.service;

import java.util.List;

import author.pojo.User;
import sale.pojo.Sale;
import utils.page.Page;



public interface SaleService {
	public Page splitPage(int pageIndex,int pageSize);
	public List<Sale> find();   		        //Ĭ�ϲ�ѯ����
	public List<Sale> find(Sale sale);   		//HQL��ѯ
	public boolean add(Sale sale);				//���
	public boolean del(Sale sale);				//ɾ��
	public boolean update(Sale sale);           //�޸�
	public List<User> find_User();
}
