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
	
//-----------------------------方法开始-----------------------------------    
	public String to(){
		//先找出需要操作的订单
		List<Orders> clist = (List<Orders>) Session.get("orderslist");
		for (int i = 0; i < clist.size(); i++) {
			int newID = clist.get(i).getID();
			if(o.getID() == newID){
				o = clist.get(i);
				Session.put("editorders", o);
			}
		}
		return this.find();   //转去查找
	}
	public String read(){   //只读
		this.to();
		return "read";
	}
	public String find(){
		//开始查询相应的订单详情--
		o = (Orders) Session.get("editorders");  //拿出要操作的客户信息，以它编号作为查询条件
		List<OrdersLine> list = ordersLineService.find(o);
		Session.put("orderslinelist", list);
		return "success";
	}
	
	public String add(){
		//---扫描一下商品信息
		List<Product> pro = ordersLineService.find_product();
		Session.put("productlist", pro);
		//开始添加------
		o = (Orders) Session.get("editorders");
		ol.setOrders(o);
		boolean flag = ordersLineService.add(ol);
		if(flag == false){
			Request.put("error_message", "新增订单详情失败！");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = ordersLineService.update(ol);
		if(flag == false){
			Request.put("error_message", "修改订单详情失败！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的联系人，转入编辑页面
		List<OrdersLine> list = (List<OrdersLine>) Session.get("orderslinelist");
		for (int i = 0; i < list.size(); i++) {
			if(ol.getID() == list.get(i).getID()){
				Request.put("editordersline", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String del(){
		boolean flag = ordersLineService.del(ol);
		if(flag == false){
			Request.put("error_message", "删除订单详情失败！");
			return "error";
		}
		return find();
	}
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
}
