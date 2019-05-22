package cust.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import utils.page.Page;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Customer;
import cust.pojo.Lost;
import cust.service.LostService;

@Controller(value="LostAction")
public class LostAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="lostService")
    private LostService lostService;
    @Resource(name="pager")
    private Page pager;
    
    private Lost l;
    private String flag;
    
    
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
	public Map<String, Object> getApplication() {
		return Application;
	}
	public void setApplication(Map<String, Object> application) {
		Application = application;
	}
	public LostService getLostService() {
		return lostService;
	}
	public void setLostService(LostService lostService) {
		this.lostService = lostService;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public Lost getL() {
		return l;
	}
	public void setL(Lost l) {
		this.l = l;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	public String find(){
		//ɨ��һ�¿ͻ���Ϣ
		List<Customer> cl = lostService.find_customer();
		Session.put("customerlist",cl);
		//��ѯ��ʼ
		pager = lostService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Lost> list = pager.getList();
		Session.put("lostlist",list);
		return "success";
	}
	public String findHQL(){
		List<Lost> list = lostService.find(l);
		Session.put("lostlist",list);
		return "success";
	}
	
	public String add(){
		l.setStatus(1);  //����״̬ΪԤ��
		boolean flag = lostService.add(l);
		if(flag == false){
			Request.put("error_message", "������ʧ��Ϣʧ��,����ÿͻ��Ƿ��Ѿ�������ʧ��Ϣ");
			return "error";
		}
		return find();
	}
	
	public String update(){
		l.setStatus(2);  //����״̬Ϊ�ݻ�
		boolean flag = lostService.update(l);
		if(flag == false){
			Request.put("error_message", "�ݻ���ʧ����");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//�������Ŀͻ���Ϣ��ת��༭ҳ��
		List<Lost> list = (List<Lost>) Session.get("lostlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(l.getID() == newID){
				Request.put("editlost", list.get(i));
				if(flag.equals("11")){
					return "read";
				}
				if(flag.equals("22")){
					return "edit";
				}
				if(flag.equals("33")){
					return "qrls";
				}
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String dolost(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		String date = sdf.format(new Date());
		l.setLostdate(date);  //������ʧʱ��
		l.setStatus(3);  //����״̬Ϊ��ʧ
		boolean flag = lostService.update(l);
		if(flag == false){
			Request.put("error_message", "ȷ����ʧ��");
			return "error";
		}
		return this.find();
	}
	
	public String del(){
		boolean flag = lostService.del(l);
		if(flag == false){
			Request.put("error_message", "ɾ����Ϣʧ�ܣ������ж�Ӧ����Ϣδɾ����");
			return "error";
		}
		return this.find();
	}
	public String rept(){
		List<Lost> list = lostService.rept(flag);
		Request.put("lostRept", list);
		return "rept";
	}
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
	
}
