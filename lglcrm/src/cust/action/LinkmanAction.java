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
		//开始查询联系人--
		c = (Customer) Session.get("editcustomer");  //拿出要操作的客户信息，以它编号作为查询条件
		List<Linkman> list = linkmanService.find(c);
		Session.put("linkmanlist", list);
		return "success";
	}
	
	public String add(){
		c = (Customer) Session.get("editcustomer");
		l.setCustomer(c);
		boolean flag = linkmanService.add(l);
		if(flag == false){
			Request.put("error_message", "新增联系人失败！");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = linkmanService.update(l);
		if(flag == false){
			Request.put("error_message", "修改联系人失败！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的联系人，转入编辑页面
		List<Linkman> list = (List<Linkman>) Session.get("linkmanlist");
		for (int i = 0; i < list.size(); i++) {
			if(l.getID() == list.get(i).getID()){
				Request.put("editlinkman", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String del(){
		boolean flag = linkmanService.del(l);
		if(flag == false){
			Request.put("error_message", "删除联系人失败！");
			return "error";
		}
		return find();
	}
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
}
