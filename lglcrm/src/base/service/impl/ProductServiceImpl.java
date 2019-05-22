package base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import utils.page.Page;
import base.dao.ProductDao;
import base.pojo.Product;
import base.service.ProductService;

@Service(value="productService")
public class ProductServiceImpl implements ProductService {
	@Resource(name="productDao")
	private ProductDao productDao;
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = productDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Product> list = productDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
		return pb;
	}

	@Override
	public List<Product> find() {
		return productDao.find();
	}

	@Override
	public List<Product> find(Product p) {
		return productDao.find(p);
	}

	@Override
	public boolean add(Product p) {
		return productDao.add(p);
	}

	@Override
	public boolean del(Product p) {
		return productDao.del(p);
	}

	@Override
	public boolean update(Product p) {
		return productDao.update(p);
	}




	
}
