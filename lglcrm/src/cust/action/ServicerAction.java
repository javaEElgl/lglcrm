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
    private int flag; //请求标识
    
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
	
//-----------------------------方法开始-----------------------------------    
	public String find(){
		//扫描客户信息表
		List<Customer> customers = svService.find_cust();
		Session.put("customerlist", customers);
		//扫描用户表
		List<User> users = svService.find_user();
		Session.put("userlist", users);
		//扫描数据字典表
		List<Dict> dicts = svService.find_dict();
		Session.put("dictlist", dicts);
		pager = svService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Servicer> list = pager.getList();
		Session.put("servicerlist",list);
		if(flag == 1){
			return "add";   //新建
		}
		if(flag == 2){
			return "dispatch";   //分配
		}
		if(flag == 3){
			return "deal";   //处理
		}
		if(flag == 4){
			return "feedback";  //反馈
		}
		if(flag == 5){
			return "read";  //归档
		}
		Request.put("error_message", "请求得服务出错，请检查！");
		return "error";
	}
	
	public String findHQL(){
		List<Servicer> list = svService.find(sv);
		Session.put("servicerlist",list);
		if(flag == 1){
			return "add";   //新建
		}
		if(flag == 2){
			return "dispatch";   //分配
		}
		if(flag == 3){
			return "deal";   //处理
		}
		if(flag == 4){
			return "feedback";  //反馈
		}
		if(flag == 5){
			return "read";  //归档
		}
		Request.put("error_message", "请求得服务出错，请检查！");
		return "error";
	}
	
	public String add(){
		sv.setStatus(1);  //设置状态 （1未分配 2已分配 3已处理 4已归档）
		boolean flag = svService.add(sv);
		if(flag == false){
			Request.put("error_message", "新增客户信息失败！");
			return "error";
		}
		return this.find();
	}
	
	public String update(){
		if(flag == 2){
			sv.setStatus(2);  //设置状态2   //分配操作
		}
		if(flag == 3){
			sv.setStatus(3);  //设置状态3 //处理操作
		}
		if(flag == 4){
			sv.setStatus(4);  //设置状态4  //反馈操作后就归档
		}
		boolean flag = svService.update(sv);
		if(flag == false){
			Request.put("error_message", "修改客户信息失败！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的客户信息，转入编辑页面
		List<Servicer> list = (List<Servicer>) Session.get("servicerlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(sv.getID() == newID){
				Request.put("editservicer", list.get(i));
				if(flag == 2){
					return "dispatch_edit";   //分配
				}
				if(flag == 3){
					return "deal_edit";   //处理
				}
				if(flag == 4){
					return "feedback_edit";  //反馈
				}
				if(flag == 5){
					return "read_edit";  //归档
				}
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String del(){
		boolean flag = svService.del(sv);
		if(flag == false){
			Request.put("error_message", "删除客户信息失败，其下有对应的信息未删除！");
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
