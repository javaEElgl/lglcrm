package base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import utils.page.Page;
import base.dao.DictDao;
import base.pojo.Dict;
import base.service.DictService;


@Service(value="dictService")
public class DictServiceImpl implements DictService {
	@Resource(name="dictDao")
	private DictDao dictDao;
	
	public DictDao getDictDao() {
		return dictDao;
	}

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	@Override
	public List<Dict> find() {
		return dictDao.find();
	}

	@Override
	public List<Dict> find(Dict d) {
		return dictDao.find(d);
	}

	@Override
	public boolean add(Dict d) {
		return dictDao.add(d);
	}

	@Override
	public boolean del(Dict d) {
		return dictDao.del(d);
	}

	@Override
	public boolean update(Dict d) {
		return dictDao.update(d);
	}

	@Override
	public Page splitPage(int pageIndex, int pageSize) {
		int allRow = dictDao.find().size();   //总记录
		int lenght = Page.getLenght(allRow, pageSize);  //页数
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //获取正确的页码值
		int offset = Page.getOffset(trueIndex, pageSize); //开始下标
		List<Dict> list = dictDao.splitPage(offset, pageSize);  //得到返回 的数据集合
		Page pb = new Page();  //新建一个页面类
		pb.setCount(allRow);    //封装总条数
		pb.setPages(lenght);	//封装页数
		pb.setPageIndex(trueIndex);  //封装当前页码
		pb.setPageSize(pageSize);    //封装页面大小
		pb.setList(list);      //封装数据
		return pb;
	}

}
