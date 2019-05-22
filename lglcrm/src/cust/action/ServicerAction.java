package cust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import utils.page.Page;
import author.pojo.User;
import base.pojo.Dict;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Customer;
import cust.pojo.Servicer;
import cust.service.ServicerService;


@Controller(value="ServicerAction")
public class ServicerAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    @Resource(name="pager")
    private Page pager;
    
    private Servicer sv;
    
    @Resource(name="servicerService")
    private ServicerService svService;
    private int flag; //�����ʶ
    
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
	
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public Servicer getSv() {
		return sv;
	}
	public void setSv(Servicer sv) {
		this.sv = sv;
	}
	public ServicerService getSvService() {
		return svService;
	}
	public void setSvService(ServicerService svService) {
		this.svService = svService;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
//-----------------------------������ʼ-----------------------------------    
	public String find(){
		//ɨ��ͻ���Ϣ��
		List<Customer> customers = svService.find_cust();
		Session.put("customerlist", customers);
		//ɨ���û���
		List<User> users = svService.find_user();
		Session.put("userlist", users);
		//ɨ�������ֵ��
		List<Dict> dicts = svService.find_dict();
		Session.put("dictlist", dicts);
		pager = svService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Servicer> list = pager.getList();
		Session.put("servicerlist",list);
		if(flag == 1){
			return "add";   //�½�
		}
		if(flag == 2){
			return "dispatch";   //����
		}
		if(flag == 3){
			return "deal";   //����
		}
		if(flag == 4){
			return "feedback";  //����
		}
		if(flag == 5){
			return "read";  //�鵵
		}
		Request.put("error_message", "����÷���������飡");
		return "error";
	}
	
	public String findHQL(){
		List<Servicer> list = svService.find(sv);
		Session.put("servicerlist",list);
		if(flag == 1){
			return "add";   //�½�
		}
		if(flag == 2){
			return "dispatch";   //����
		}
		if(flag == 3){
			return "deal";   //����
		}
		if(flag == 4){
			return "feedback";  //����
		}
		if(flag == 5){
			return "read";  //�鵵
		}
		Request.put("error_message", "����÷���������飡");
		return "error";
	}
	
	public String add(){
		sv.setStatus(1);  //����״̬ ��1δ���� 2�ѷ��� 3�Ѵ��� 4�ѹ鵵��
		boolean flag = svService.add(sv);
		if(flag == false){
			Request.put("error_message", "�����ͻ���Ϣʧ�ܣ�");
			return "error";
		}
		return this.find();
	}
	
	public String update(){
		if(flag == 2){
			sv.setStatus(2);  //����״̬2   //�������
		}
		if(flag == 3){
			sv.setStatus(3);  //����״̬3 //�������
		}
		if(flag == 4){
			sv.setStatus(4);  //����״̬4  //����������͹鵵
		}
		boolean flag = svService.update(sv);
		if(flag == false){
			Request.put("error_message", "�޸Ŀͻ���Ϣʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//�������Ŀͻ���Ϣ��ת��༭ҳ��
		List<Servicer> list = (List<Servicer>) Session.get("servicerlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(sv.getID() == newID){
				Request.put("editservicer", list.get(i));
				if(flag == 2){
					return "dispatch_edit";   //����
				}
				if(flag == 3){
					return "deal_edit";   //����
				}
				if(flag == 4){
					return "feedback_edit";  //����
				}
				if(flag == 5){
					return "read_edit";  //�鵵
				}
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = svService.del(sv);
		if(flag == false){
			Request.put("error_message", "ɾ���ͻ���Ϣʧ�ܣ������ж�Ӧ����Ϣδɾ����");
			return "error";
		}
		return this.find();
	}
	public String rept(){
		List<Object[]> list = svService.rept(flag);
		Request.put("servicerRept", list);
		return "rept";
	}
}
