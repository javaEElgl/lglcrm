package sale.service.imple;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import author.dao.UserDao;
import author.pojo.User;
import sale.dao.SaleDao;
import sale.pojo.Sale;
import sale.service.SaleService;
import utils.page.Page;


@Service(value="saleService")
public class SaleServiceImpl implements SaleService {
	@Resource(name="saleDao")
	private SaleDao saleDao;
	@Resource(name="userDao")
	private UserDao userDao;
	
	public SaleDao getSaleDao() {
		return saleDao;
	}

	public void setSaleDao(SaleDao saleDao) {
		this.saleDao = saleDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = saleDao.find().size();   //总记录
		int lenght = Page.getLenght(allRow, pageSize);  //页数
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //获取正确的页码值
		int offset = Page.getOffset(trueIndex, pageSize); //起始码
		List<Sale> list = saleDao.splitPage(offset, pageSize);  //得到返回 的数据集合
		Page pb = new Page();  //新建一个页面类
		pb.setCount(allRow);    //封装总条数
		pb.setPages(lenght);	//封装页数
		pb.setPageIndex(trueIndex);  //封装正确页码
		pb.setPageSize(pageSize);    //封装页面大小
		pb.setList(list);      //封装数据
		return pb;
	}
	
	@Override
	public List<Sale> find() {
		return saleDao.find();
	}
	
	@Override
	public List<Sale> find(Sale sale) {
		return saleDao.find(sale);
	}

	@Override
	public boolean add(Sale sale) {
		return saleDao.add(sale);
	}

	@Override
	public boolean del(Sale sale) {
		return saleDao.del(sale);
	}

	@Override
	public boolean update(Sale sale) {
		return saleDao.update(sale);
	}

	@Override
	public List<User> find_User() {
		return userDao.find();
	}
	
	
}
