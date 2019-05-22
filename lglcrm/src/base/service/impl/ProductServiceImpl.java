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
		int allRow = productDao.find().size();   //总记录
		int lenght = Page.getLenght(allRow, pageSize);  //页数
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //获取正确的页码值
		int offset = Page.getOffset(trueIndex, pageSize); //开始下标
		List<Product> list = productDao.splitPage(offset, pageSize);  //得到返回 的数据集合
		Page pb = new Page();  //新建一个页面类
		pb.setCount(allRow);    //封装总条数
		pb.setPages(lenght);	//封装页数
		pb.setPageIndex(trueIndex);  //封装当前页码
		pb.setPageSize(pageSize);    //封装页面大小
		pb.setList(list);      //封装数据
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
