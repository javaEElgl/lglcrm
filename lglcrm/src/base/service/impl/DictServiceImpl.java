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
		int allRow = dictDao.find().size();   //�ܼ�¼
		int lenght = Page.getLenght(allRow, pageSize);  //ҳ��
		int trueIndex = Page.gettrueIndex(pageIndex, lenght);  //��ȡ��ȷ��ҳ��ֵ
		int offset = Page.getOffset(trueIndex, pageSize); //��ʼ�±�
		List<Dict> list = dictDao.splitPage(offset, pageSize);  //�õ����� �����ݼ���
		Page pb = new Page();  //�½�һ��ҳ����
		pb.setCount(allRow);    //��װ������
		pb.setPages(lenght);	//��װҳ��
		pb.setPageIndex(trueIndex);  //��װ��ǰҳ��
		pb.setPageSize(pageSize);    //��װҳ���С
		pb.setList(list);      //��װ����
		return pb;
	}

}
