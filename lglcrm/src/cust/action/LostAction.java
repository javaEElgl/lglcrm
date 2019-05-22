package cust.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import utils.page.Page;

import com.opensymphony.xwork2.ActionSupport;

import cust.pojo.Customer;
import cust.pojo.Lost;
import cust.service.LostService;

@Controller(value="LostAction")
public class LostAction extends ActionSupport implements SessionAware,RequestAware,ApplicationAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="lostService")
    private LostService lostService;
    @Resource(name="pager")
    private Page pager;
    
    private Lost l;
    private String flag;
    
    
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
	public LostService getLostService() {
		return lostService;
	}
	public void setLostService(LostService lostService) {
		this.lostService = lostService;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public Lost getL() {
		return l;
	}
	public void setL(Lost l) {
		this.l = l;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	public String find(){
		//扫描一下客户信息
		List<Customer> cl = lostService.find_customer();
		Session.put("customerlist",cl);
		//查询开始
		pager = lostService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Lost> list = pager.getList();
		Session.put("lostlist",list);
		return "success";
	}
	public String findHQL(){
		List<Lost> list = lostService.find(l);
		Session.put("lostlist",list);
		return "success";
	}
	
	public String add(){
		l.setStatus(1);  //设置状态为预警
		boolean flag = lostService.add(l);
		if(flag == false){
			Request.put("error_message", "新增流失信息失败,请检查该客户是否已经存在流失信息");
			return "error";
		}
		return find();
	}
	
	public String update(){
		l.setStatus(2);  //设置状态为暂缓
		boolean flag = lostService.update(l);
		if(flag == false){
			Request.put("error_message", "暂缓流失出错！");
			return "error";
		}
		return find();
	}
	
	public String edit(){
		//查出点击的客户信息，转入编辑页面
		List<Lost> list = (List<Lost>) Session.get("lostlist");
		for (int i = 0; i < list.size(); i++) {
			int newID = list.get(i).getID();
			if(l.getID() == newID){
				Request.put("editlost", list.get(i));
				if(flag.equals("11")){
					return "read";
				}
				if(flag.equals("22")){
					return "edit";
				}
				if(flag.equals("33")){
					return "qrls";
				}
			}
		}
		Request.put("error_message", "检索出错，请重新进入该功能模块！");
		return "error";
	}
	
	public String dolost(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		String date = sdf.format(new Date());
		l.setLostdate(date);  //设置流失时间
		l.setStatus(3);  //设置状态为流失
		boolean flag = lostService.update(l);
		if(flag == false){
			Request.put("error_message", "确认流失！");
			return "error";
		}
		return this.find();
	}
	
	public String del(){
		boolean flag = lostService.del(l);
		if(flag == false){
			Request.put("error_message", "删除信息失败，其下有对应的信息未删除！");
			return "error";
		}
		return this.find();
	}
	public String rept(){
		List<Lost> list = lostService.rept(flag);
		Request.put("lostRept", list);
		return "rept";
	}
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
	
}
