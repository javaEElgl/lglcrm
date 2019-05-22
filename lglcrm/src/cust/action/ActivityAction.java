package cust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Activity;
import cust.pojo.Customer;
import cust.service.ActivityService;

@Controller(value="ActivityAction")
public class ActivityAction extends ActionSupport implements SessionAware,RequestAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    
    @Resource(name="activityService")
    private ActivityService activityService;
    private Customer c;
    private Activity a;
    
    
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
	
	public ActivityService getActivityService() {
		return activityService;
	}
	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Activity getA() {
		return a;
	}
	public void setA(Activity a) {
		this.a = a;
	}
	
    

	public String to(){
		//���ҳ���Ҫ�����Ŀͻ���Ϣ
		List<Customer> clist = (List<Customer>) Session.get("customerlist");
		for (int i = 0; i < clist.size(); i++) {
			int newID = clist.get(i).getID();
			if(c.getID() == newID){
				c = clist.get(i);
				Session.put("editcustomer", c);
			}
		}
		return this.find();   //תȥ����
	}
	public String find(){
		//��ʼ��ѯ��ʷ��¼--
		c = (Customer) Session.get("editcustomer");  //�ó�Ҫ�����Ŀͻ���Ϣ�����������Ϊ��ѯ����
		List<Activity> list = activityService.find(c);
		Session.put("activitylist", list);
		return "success";
	}
	
	public String add(){
		c = (Customer) Session.get("editcustomer");
		a.setCustomer(c);
		boolean flag = activityService.add(a);
		if(flag == false){
			Request.put("error_message", "������¼ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = activityService.update(a);
		if(flag == false){
			Request.put("error_message", "�޸ļ�¼ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//����������ϵ�ˣ�ת��༭ҳ��
		List<Activity> list = (List<Activity>) Session.get("activitylist");
		for (int i = 0; i < list.size(); i++) {
			if(a.getID() == list.get(i).getID()){
				Request.put("editactivity", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = activityService.del(a);
		if(flag == false){
			Request.put("error_message", "ɾ����¼ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
	
}
