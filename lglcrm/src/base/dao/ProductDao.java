package base.dao;

import java.util.List;

import base.pojo.Product;


public interface ProductDao {
	public List<Product> splitPage(int offset,int pageSize);   //��ҳ��ѯ
	public List<Product> find();                //��ѯ����
	public List<Product> find(Product p);   	//ģ����ѯ
	public boolean add(Product p);      	//���
	public boolean del(Product p);      	//ɾ��
	public boolean update(Product p);   	//�޸�
}
