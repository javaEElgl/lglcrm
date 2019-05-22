package cust.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import base.pojo.Product;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Orders;
import cust.pojo.OrdersLine;
import cust.service.OrdersLineService;

@Controller(value="OrdersLineAction")
public class OrdersLineAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="ordersLineService")
    private OrdersLineService ordersLineService;
    private Orders o;
    private OrdersLine ol;
    
    
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
	public OrdersLineService getOrdersLineService() {
		return ordersLineService;
	}
	public void setOrdersLineService(OrdersLineService ordersLineService) {
		this.ordersLineService = ordersLineService;
	}
	public Orders getO() {
		return o;
	}
	public void setO(Orders o) {
		this.o = o;
	}
	public OrdersLine getOl() {
		return ol;
	}
	public void setOl(OrdersLine ol) {
		this.ol = ol;
	}
	
//-----------------------------������ʼ-----------------------------------    
	public String to(){
		//���ҳ���Ҫ�����Ķ���
		List<Orders> clist = (List<Orders>) Session.get("orderslist");
		for (int i = 0; i < clist.size(); i++) {
			int newID = clist.get(i).getID();
			if(o.getID() == newID){
				o = clist.get(i);
				Session.put("editorders", o);
			}
		}
		return this.find();   //תȥ����
	}
	public String read(){   //ֻ��
		this.to();
		return "read";
	}
	public String find(){
		//��ʼ��ѯ��Ӧ�Ķ�������--
		o = (Orders) Session.get("editorders");  //�ó�Ҫ�����Ŀͻ���Ϣ�����������Ϊ��ѯ����
		List<OrdersLine> list = ordersLineService.find(o);
		Session.put("orderslinelist", list);
		return "success";
	}
	
	public String add(){
		//---ɨ��һ����Ʒ��Ϣ
		List<Product> pro = ordersLineService.find_product();
		Session.put("productlist", pro);
		//��ʼ���------
		o = (Orders) Session.get("editorders");
		ol.setOrders(o);
		boolean flag = ordersLineService.add(ol);
		if(flag == false){
			Request.put("error_message", "������������ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = ordersLineService.update(ol);
		if(flag == false){
			Request.put("error_message", "�޸Ķ�������ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//����������ϵ�ˣ�ת��༭ҳ��
		List<OrdersLine> list = (List<OrdersLine>) Session.get("orderslinelist");
		for (int i = 0; i < list.size(); i++) {
			if(ol.getID() == list.get(i).getID()){
				Request.put("editordersline", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "�������������½���ù���ģ�飡");
		return "error";
	}
	
	public String del(){
		boolean flag = ordersLineService.del(ol);
		if(flag == false){
			Request.put("error_message", "ɾ����������ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
}
