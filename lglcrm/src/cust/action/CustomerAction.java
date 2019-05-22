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
    private String flag = "level"; //统计报表方式请求标识   ‘level 按等级, 'region' 按地区  ,默认按等级显示
    
    
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
	
	//查询所有的顾客信息
	public String find(){
		//扫描用户表
		List<User> users = customerService.find_user();
		Session.put("userlist", users);
		//扫描数据字典表
		List<Dict> dicts = customerService.find_dict();
		Session.put("dictlist", dicts);
		//开始查询客户信息--
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
			Request.put("error_message", "新增客户信息失败,请检查是否已经存在该客户名！");
			return "error";
		}
		return find();
	}
	
	public String update(){
		boolean flag = customerService.update(c);
		if(flag == false){
			Request.put("error_message", "修改客户信息失败！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的客户信息，转入编辑页面
		List<Customer> list = (List<Customer>) Session.get("customerlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(c.getID() == newID){
				Request.put("editcustomer", list.get(i));
				return "edit";
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String del(){
		boolean flag = customerService.del(c);
		if(flag == false){
			Request.put("error_message", "删除客户信息失败，其下有对应的信息未删除！");
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
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}

}
