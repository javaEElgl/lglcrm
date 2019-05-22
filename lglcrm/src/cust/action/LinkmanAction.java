package cust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Customer;
import cust.pojo.Linkman;
import cust.service.LinkmanService;

@Controller(value="LinkmanAction")
public class LinkmanAction extends ActionSupport implements SessionAware,RequestAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    
    @Resource(name="linkmanService")
    private LinkmanService linkmanService;
    private Customer c;
    private Linkman l;
    
    
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
	public LinkmanService getLinkmanService() {
		return linkmanService;
	}
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Linkman getL() {
		return l;
	}
	public void setL(Linkman l) {
		this.l = l;
	}
//-----------------------------������ʼ-----------------------------------    
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
		//��ʼ��ѯ��ϵ��--
		c = (Customer) Session.get("editcustomer");  //�ó�Ҫ�����Ŀͻ���Ϣ�����������Ϊ��ѯ����
		List<Linkman> list = linkmanService.find(c);
		Session.put("linkmanlist", list);
		return "success";
	}
	
	public String add(){
		c = (Customer) Session.get("editcustomer");
		l.setCustomer(c);
		boolean flag = linkmanService.add(l);
		if(flag == false){
			Request.put("error_message", "������ϵ��ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = linkmanService.update(l);
		if(flag == false){
			Request.put("error_message", "�޸���ϵ��ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//����������ϵ�ˣ�ת��༭ҳ��
		List<Linkman> list = (List<Linkman>) Session.get("linkmanlist");
		for (int i = 0; i < list.size(); i++) {
			if(l.getID() == list.get(i).getID()){
				Request.put("editlinkman", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = linkmanService.del(l);
		if(flag == false){
			Request.put("error_message", "ɾ����ϵ��ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
}
