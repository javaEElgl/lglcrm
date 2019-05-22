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
	
//-----------------------------方法开始-----------------------------------    
	public String to(){
		//先找出需要操作的客户信息
		List<Customer> clist = (List<Customer>) Session.get("customerlist");
		for (int i = 0; i < clist.size(); i++) {
			int newID = clist.get(i).getID();
			if(c.getID() == newID){
				c = clist.get(i);
				Session.put("editcustomer", c);
			}
		}
		return this.find();   //转去查找
	}
	public String find(){
		//开始查询历史订单--
		c = (Customer) Session.get("editcustomer");  //拿出要操作的客户信息，以它编号作为查询条件
		List<Orders> list = ordersService.find(c);
		Session.put("orderslist", list);
		return "success";
	}
	
	public String add(){
		c = (Customer) Session.get("editcustomer");
		o.setCustomer(c);
		boolean flag = ordersService.add(o);
		if(flag == false){
			Request.put("error_message", "新增订单失败！");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = ordersService.update(o);
		if(flag == false){
			Request.put("error_message", "修改订单失败！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的订单，转入编辑页面
		List<Orders> list = (List<Orders>) Session.get("orderslist");
		for (int i = 0; i < list.size(); i++) {
			if(o.getID() == list.get(i).getID()){
				o = list.get(i);
				Request.put("editorders", o);
				return "edit";
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String del(){
		boolean flag = ordersService.del(o);
		if(flag == false){
			Request.put("error_message", "删除订单失败，已产生订单明细。");
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
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
}
