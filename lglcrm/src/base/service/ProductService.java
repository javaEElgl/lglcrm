package base.service;

import java.util.List;

import base.pojo.Product;
import utils.page.Page;



public interface ProductService {
	public Page splitPage(int pageIndex,int pageSize);   	//��ҳ��ѯ
	public List<Product> find();            //��ѯ����
	public List<Product> find(Product p);   	//ģ����ѯ
	public boolean add(Product p);      	//���
	public boolean del(Product p);      	//ɾ��
	public boolean update(Product p);   	//�޸�
}
