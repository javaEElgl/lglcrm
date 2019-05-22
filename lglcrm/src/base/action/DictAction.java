package base.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import utils.page.Page;
import base.pojo.Dict;
import base.service.DictService;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="DictAction")
public class DictAction extends ActionSupport implements RequestAware,SessionAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="dictService")
    private DictService dictService;
    
    @Resource(name="pager")
    private Page pager;
    private Dict d;
    
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
	public DictService getDictService() {
		return dictService;
	}
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public Dict getD() {
		return d;
	}
	public void setD(Dict d) {
		this.d = d;
	}
//---------------方法开始-------
	
	public String find(){
		pager = dictService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Dict> list = pager.getList();
		Session.put("dictlist",list);
		return "success";
	}
	public String findHQL(){
		List<Dict> list = dictService.find(d);
		Session.put("dictlist",list);
		return "success";
	}
	public String add(){
		boolean flag = dictService.add(d);
		if(flag == false){
			Request.put("error_message", "新增字典失败，请仔细核对填写信息！");
			return "error";
		}
		return this.find();
	}
	public String update(){
		boolean flag = dictService.update(d);
		if(flag == false){
			Request.put("error_message", "修改字典失败，请仔细核对填写信息！");
			return "error";
		}
		return this.find();
	}
	public String del(){
		boolean flag = dictService.del(d);
		if(flag == false){
			Request.put("error_message", "删除字典数据失败，请重试！");
			return "error";
		}
		return this.find();
	}
	public String edit(){
		List<Dict> list = (List<Dict>) Session.get("dictlist");
		for (int i = 0; i < list.size(); i++) {
			if(d.getID() == list.get(i).getID()){
				d = list.get(i);
				Request.put("editdict", d);
			}
		}
		return "edit";
	}
	
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
}
