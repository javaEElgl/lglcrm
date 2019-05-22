package sale.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import sale.pojo.Plan;
import sale.pojo.Sale;
import sale.service.PlanService;
import sale.service.SaleService;
import utils.page.Page;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="PlanAction")
public class PlanAction extends ActionSupport implements SessionAware,RequestAware{
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="pager")
    private Page pager;
    @Resource(name="planService")
    private PlanService planService;
    @Resource(name="saleService")
    private SaleService saleService;
    
    private Plan p;
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
	public PlanService getPlanService() {
		return planService;
	}
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public Plan getP() {
		return p;
	}
	public void setP(Plan p) {
		this.p = p;
	}
	public Sale getS() {
		return s;
	}
	public void setS(Sale s) {
		this.s = s;
	}
//-------方法开始------------------
	public String find(){
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
		s.setStatus(3);  //设置此Sale状态为 3已制定计划
		saleService.update(s);  
		p.setSale(s);   //把s放入plan中的外键
		boolean flag = planService.add(p);
		if(flag == false){
			Request.put("error_message", "添加计划信息出错！");
			return "error";
		}
		return this.zdjh();
	}
	public String del(){
		boolean flag = planService.del(p);
		if(flag == false){
			Request.put("error_message", "删除计划信息出错！");
			return "error";
		}
		return this.zdjh();
	}
	
	public String update(){
		boolean flag = planService.update(p);
		if(flag == false){
			Request.put("error_message", "修改计划信息出错！");
			return "error";
		}
		return this.zdjh();
	}
	public String saveresult(){
		boolean flag = planService.update(p);
		if(flag == false){
			Request.put("error_message", "保存结果信息出错！");
			return "error";
		}
		return this.execute();
	}
	public String zdjh(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				Request.put("editsale",s);
			}
		}
		//查询之前有的计划
		List<Plan> plans = planService.find(s);
		Request.put("planlist",plans);
		if(s.getStatus() !=2 && s.getStatus() !=3){
			Request.put("error_message", "当前销售机会‘未指派’或‘已在开发中’！");
			return "error";
		}
		return "zdjh";
	}
	public String execute(){
		this.zdjh();
		if(s.getStatus() !=3 && s.getStatus() !=4){
			Request.put("error_message", "当前销售机会‘未指派’或‘还未制定开发计划’！");
			return "error";
		}
		s.setStatus(4);  //设置销售机会状态为4 开发中
		saleService.update(s); //修改这个sale
		//----------
		return "execute";
	}
	public String read(){
		this.zdjh();
		return "read";
	}
	public String over(){
		saleService.update(s);
		return find();
	}
	
	public String toadd(){
		//添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
    
}
