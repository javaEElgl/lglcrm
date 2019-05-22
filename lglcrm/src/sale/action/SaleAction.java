package sale.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import sale.pojo.Sale;
import sale.service.SaleService;
import utils.page.Page;
import author.pojo.User;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="SaleAction")
public class SaleAction extends ActionSupport implements SessionAware,RequestAware{
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="pager")
    private Page pager;   //���ڻ�ȡ��ҳ��ѯ��ʾ
    
    @Resource(name="saleService")
    private SaleService saleService;
    private Sale s;
    
	public Map<String, Object> getSession() {
		return Session;
	}
	public void setSession(Map<String, Object> session) {
		Session = session;
	}
	public Map<String, Object> getRequest() {
		return Request;
	}
	public void setRequest(Map<String, Object> request) {
		Request = request;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public Sale getS() {
		return s;
	}
	public void setS(Sale s) {
		this.s = s;
	}
	
	
	
	public String find(){
		//��ɨ��һ�����µ��û��б�
		List<User> users = saleService.find_User();
		Session.put("userlist", users);
		//��ʼ��ѯ
		pager = saleService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Sale> list = pager.getList();
		Session.put("salelist",list);
		return "success";
	}
	
	public String findHQL(){
		List<Sale> list = saleService.find(s);
		Session.put("salelist",list);
		return "success";
	}
	
	public String add(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		s.setCreateTime(time);  //���ô������ۻ����ʱ��Ϊ��ǰʱ��
		if(s.getDueName().equals("δָ��")){   //�ж��Ƿ��Ѿ�ָ�ɡ�
			s.setDueName(null);       //��ָ��������Ϊ��
			s.setStatus(1);         //�������ۻ���״̬(1 δָ�� 2 ��ָ�� 3 �Ѽƻ� 4 ������ 5 ����ʧ�� 6�����ɹ�)
		}else{
			s.setStatus(2);   //����״̬Ϊ2
			s.setDueTime(time);  //ͬʱ����ָ��ʱ��
			
		}
		boolean flag = saleService.add(s);
		if(flag == false){
			Request.put("error_message", "����ʧ�ܣ�����ϸ�˶��������Ϣ");
			return "error";
		}
		return find();
	}
	public String del(){
		boolean flag = saleService.del(s);
		if(flag == false){
			Request.put("error_message", "�����ۻ����Ѳ���������¼��������ɾ����");
			return "error";
		}
		return find();
	}
	
	public String update(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		if(s.getDueName().equals("δָ��")){   //�ж��Ƿ��Ѿ�ָ�ɡ�
			s.setDueName(null);       //��ָ��������Ϊ��
			s.setStatus(1);         //(1 δָ�� 2 ��ָ�� 3 ���ƶ��ƻ� 4 ������ 5 ����ʧ�� 6�����ɹ�)
		}else{
			s.setStatus(2);   //����״̬Ϊ2
			s.setDueTime(time);  //ͬʱ����ָ��ʱ��
			
		}
		boolean flag = saleService.update(s);
		if(flag == false){
			Request.put("error_message", "�޸�ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	public String edit(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				if(s.getStatus() !=1 && s.getStatus() !=2){
					//���жϴ˻����״̬��Ϊ1��2ʱ�� ���������ٱ༭��ָ��
					Request.put("error_message", "�����ۻ��ᴦ��'���ƶ��ƻ�'�򡮿����С����ѹ鵵��״̬���������ٱ༭!");
					return "error";
				}
			}
		}
		Request.put("editsale", s);
		return "edit";
	}
	public String dispatch(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				if(s.getStatus() != 1){
					//������۵�״̬��Ϊ1�����Ѿ�ָ�ɹ��ˡ�
					Request.put("error_message", "�����ۻ�����ָ�ɣ������޸ģ������±༭��");
					return "error";
				}
			}
		}
		Request.put("editsale", s);
		return "dispatch";
	}
	
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
	
}
