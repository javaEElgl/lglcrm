package cust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Customer;
import cust.pojo.Orders;
import cust.service.OrdersService;

@Controller(value="OrdersAction")
public class OrdersAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="ordersService")
    private OrdersService ordersService;
    private Customer c;
    private Orders o;
    
    
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
	public OrdersService getOrdersService() {
		return ordersService;
	}
	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public Orders getO() {
		return o;
	}
	public void setO(Orders o) {
		this.o = o;
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
		//��ʼ��ѯ��ʷ����--
		c = (Customer) Session.get("editcustomer");  //�ó�Ҫ�����Ŀͻ���Ϣ�����������Ϊ��ѯ����
		List<Orders> list = ordersService.find(c);
		Session.put("orderslist", list);
		return "success";
	}
	
	public String add(){
		c = (Customer) Session.get("editcustomer");
		o.setCustomer(c);
		boolean flag = ordersService.add(o);
		if(flag == false){
			Request.put("error_message", "��������ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = ordersService.update(o);
		if(flag == false){
			Request.put("error_message", "�޸Ķ���ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//�������Ķ�����ת��༭ҳ��
		List<Orders> list = (List<Orders>) Session.get("orderslist");
		for (int i = 0; i < list.size(); i++) {
			if(o.getID() == list.get(i).getID()){
				o = list.get(i);
				Request.put("editorders", o);
				return "edit";
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = ordersService.del(o);
		if(flag == false){
			Request.put("error_message", "ɾ������ʧ�ܣ��Ѳ���������ϸ��");
			return "error";
		}
		return find();
	}
	public String rept(){
		List<Object[]> list = ordersService.rept();
		Request.put("ordersRept", list);
		return "rept";
	}
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
}
