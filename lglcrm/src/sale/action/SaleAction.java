package sale.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import sale.pojo.Sale;
import sale.service.SaleService;
import utils.page.Page;
import author.pojo.User;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="SaleAction")
public class SaleAction extends ActionSupport implements SessionAware,RequestAware{
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="pager")
    private Page pager;   //用于获取分页查询显示
    
    @Resource(name="saleService")
    private SaleService saleService;
    private Sale s;
    
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
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public Sale getS() {
		return s;
	}
	public void setS(Sale s) {
		this.s = s;
	}
	
	
	
	public String find(){
		//先扫描一下最新的用户列表
		List<User> users = saleService.find_User();
		Session.put("userlist", users);
		//开始查询
		pager = saleService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Sale> list = pager.getList();
		Session.put("salelist",list);
		return "success";
	}
	
	public String findHQL(){
		List<Sale> list = saleService.find(s);
		Session.put("salelist",list);
		return "success";
	}
	
	public String add(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		s.setCreateTime(time);  //设置创建销售机会的时间为当前时间
		if(s.getDueName().equals("未指派")){   //判断是否已经指派。
			s.setDueName(null);       //被指派人设置为空
			s.setStatus(1);         //设置销售机会状态(1 未指派 2 已指派 3 已计划 4 开发中 5 开发失败 6开发成功)
		}else{
			s.setStatus(2);   //设置状态为2
			s.setDueTime(time);  //同时设置指派时间
			
		}
		boolean flag = saleService.add(s);
		if(flag == false){
			Request.put("error_message", "新增失败，请仔细核对输入的信息");
			return "error";
		}
		return find();
	}
	public String del(){
		boolean flag = saleService.del(s);
		if(flag == false){
			Request.put("error_message", "该销售机会已产生开发记录，不允许删除！");
			return "error";
		}
		return find();
	}
	
	public String update(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		if(s.getDueName().equals("未指派")){   //判断是否已经指派。
			s.setDueName(null);       //被指派人设置为空
			s.setStatus(1);         //(1 未指派 2 已指派 3 已制定计划 4 开发中 5 开发失败 6开发成功)
		}else{
			s.setStatus(2);   //设置状态为2
			s.setDueTime(time);  //同时设置指派时间
			
		}
		boolean flag = saleService.update(s);
		if(flag == false){
			Request.put("error_message", "修改失败！");
			return "error";
		}
		return find();
	}
	public String edit(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				if(s.getStatus() !=1 && s.getStatus() !=2){
					//先判断此机会的状态不为1和2时， 都不允许再编辑和指派
					Request.put("error_message", "该销售机会处于'已制定计划'或‘开发中’或‘已归档’状态，不允许再编辑!");
					return "error";
				}
			}
		}
		Request.put("editsale", s);
		return "edit";
	}
	public String dispatch(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				if(s.getStatus() != 1){
					//如果销售的状态不为1，则已经指派过了。
					Request.put("error_message", "该销售机会已指派，如需修改，请重新编辑！");
					return "error";
				}
			}
		}
		Request.put("editsale", s);
		return "dispatch";
	}
	
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
	
}
