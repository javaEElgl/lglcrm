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
import cust.service.CustomerService;

@Controller(value="CustomerAction")
public class CustomerAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="pager")
    private Page pager;
    private Customer c;
    
    @Resource(name="customerService")
    private CustomerService customerService;
    private String flag = "level"; //ͳ�Ʊ���ʽ�����ʶ   ��level ���ȼ�, 'region' ������  ,Ĭ�ϰ��ȼ���ʾ
    
    
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
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	//��ѯ���еĹ˿���Ϣ
	public String find(){
		//ɨ���û���
		List<User> users = customerService.find_user();
		Session.put("userlist", users);
		//ɨ�������ֵ��
		List<Dict> dicts = customerService.find_dict();
		Session.put("dictlist", dicts);
		//��ʼ��ѯ�ͻ���Ϣ--
		pager = customerService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Customer> list = pager.getList();
		Session.put("customerlist",list);
		return "success";
	}
	
	public String findHQL(){
		List<Customer> list = customerService.find(c);
		Session.put("customerlist",list);
		return "success";
	}
	
	public String add(){
		boolean flag = customerService.add(c);
		if(flag == false){
			Request.put("error_message", "�����ͻ���Ϣʧ��,�����Ƿ��Ѿ����ڸÿͻ�����");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = customerService.update(c);
		if(flag == false){
			Request.put("error_message", "�޸Ŀͻ���Ϣʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//�������Ŀͻ���Ϣ��ת��༭ҳ��
		List<Customer> list = (List<Customer>) Session.get("customerlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(c.getID() == newID){
				Request.put("editcustomer", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = customerService.del(c);
		if(flag == false){
			Request.put("error_message", "ɾ���ͻ���Ϣʧ�ܣ������ж�Ӧ����Ϣδɾ����");
			return "error";
		}
		return this.find();
	}
	
	
	public String rept(){
		List<Object[]> list = customerService.rept(flag);
		Request.put("custRept", list);
		return "rept";
	}
	
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}

}
