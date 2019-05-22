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
		int allRow = saleDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ��
		List<Sale> list = saleDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ȷҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
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
